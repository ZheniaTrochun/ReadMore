<template>
  <md-card>
    <md-card-content class="comment-holder">

      <md-button v-if="comment.deletable" @click="deleteComment" class="md-accent remove-comment">X</md-button>

      <h4>
        Author: {{ comment.author }}
        <br/>
        Rating: {{ comment.rating }}
        <br/>
        Comment: {{ comment.description }}
      </h4>

    </md-card-content>
  </md-card>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'

  export default {
    name: 'comment',

    props: {
      comment: {
        type: Object,
        required: true
      },
      book: {
        type: Object,
        required: true
      }
    },

    methods: {
      deleteComment() {
        axios({
          method: 'delete',
          url: '/book/review',
          params: {
            bookId: this.book.id,
            id: this.comment.id
          },
          headers: {
            'authorization': getToken()
          }
        })
          .then((res) => {
            console.log("comment removed")
            this.$emit('removedComment', this.comment)
          })
          .catch((err) => console.error(err))
      }
    }
  }
</script>

<style scoped>
  .md-card {
    width: 75%;
    margin: 4px;
    display: inline-block;
    vertical-align: top;
  }

  .container {
    position: relative !important;
    margin: auto;
    padding-top: 120px;
    padding-bottom: 50px;
    width: 80%;
    background-color: #333;
    height: 100vh;
  }

  .md-card-content {
    display: flex;
  }

  .image-holder {
    width: 30%;
    min-width: 300px;
  }

  .image-holder img {
    min-width: 200px;
  }

  .text-holder {
    width: 60%;
    min-width: 300px;
  }

  .md-dialog {
    width: 80%;
  }

  .md-card {
    width: 100%;
  }

  .comment-holder {
    position: relative;
  }

  .remove-comment {
    position: absolute;
    right: 10px;
    top: 10px;
  }
</style>

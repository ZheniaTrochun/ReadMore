<template>
  <div class="comments-holder">

      <h2>{{ book.name }}</h2>

      <comment v-for="comment in comments" :comment="comment" :book="book" @removedComment="onRemoved"/>

      <md-field>
        <label for="rating">Rating</label>
        <md-select v-model="rating" name="rating" id="rating" placeholder="0">
          <md-option value="1"><md-icon>star</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon></md-option>
          <md-option value="2"><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon></md-option>
          <md-option value="3"><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star_border</md-icon><md-icon>star_border</md-icon></md-option>
          <md-option value="4"><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star_border</md-icon></md-option>
          <md-option value="5"><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon><md-icon>star</md-icon></md-option>
        </md-select>
      </md-field>


      <md-field>
        <label>Review</label>
        <md-textarea rows="10" v-model="description"></md-textarea>
        <md-icon>description</md-icon>
      </md-field>

      <md-card-actions>
        <md-button class="md-primary" v-if="book.id" @click="add">Add Comment</md-button>
      </md-card-actions>

  </div>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import BookExt from "./BookExtended.vue";
  import Comment from "./Comment.vue";

  export default {
    components: {Comment},
    name: 'comment-list',

    props: {
      book: {
        type: Object,
        required: true
      }
    },

    mounted() {
      this.update()
    },

    methods: {
      update() {
        axios.get(
          '/book/review',
          {
            headers: {
              'authorization': getToken()
            },
            params: {
              'bookId': this.book.id
            }
          })
          .then((res) => {
            this.comments = res.data
          })
          .catch((err) => console.error(err))
      },

      add() {
        const axiosConfig = {
          headers: {
            'authorization': getToken()
          }
        }

        const data = {
          rating: this.rating,
          description: this.description,
          bookId: this.book.id
        }

        axios.post('/book/review', data, axiosConfig)
          .catch((err) => console.error(err))
          .then(() => this.update())
      },

      onRemoved(comment) {
        this.comments = this.comments.filter((c) => c !== comment)
        this.$emit('commentRemoved', '')
      }
    },

    data () {
      return {
        rating: 0,
        description: '',

        comments: []
      }
    }
  }
</script>

<style>
  .comments-holder {
    padding: 50px;
    overflow: auto;
  }

  .md-select-menu {
    z-index: 10 !important;
    width: 280px;
  }

  .md-select {
    width: 280px !important;
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
</style>

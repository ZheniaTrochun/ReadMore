<template>
  <div class="comments-holder">

      <h2>{{ book.name }}</h2>

      <comment v-for="comment in comments" :comment="comment" />

      <md-field>
        <md-select v-model="rating" name="rating" id="rating" placeholder="0">
          <md-option value="1">1</md-option>
          <md-option value="2">2</md-option>
          <md-option value="3">3</md-option>
          <md-option value="4">4</md-option>
          <md-option value="5">5</md-option>
          <md-option value="6">6</md-option>
          <md-option value="7">7</md-option>
          <md-option value="8">8</md-option>
          <md-option value="9">9</md-option>
          <md-option value="10">10</md-option>
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
      axios.get(
        'http://localhost:8080/book/review',
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

    methods: {
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

        axios.post('http://localhost:8080/book/review', data, axiosConfig)
          .catch((err) => console.error(err))
//          .then(() => this.$emit('processed', book))
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

<style scoped>
  .comments-holder {
    padding: 50px;
    overflow: auto;
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

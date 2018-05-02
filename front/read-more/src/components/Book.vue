<template>
  <md-card>
    <md-ripple>
      <md-card-header>
        <div class="md-title">{{ book.name }}</div>
        <div class="md-subhead">{{ book.author }}</div>
      </md-card-header>

      <md-card-content>
        <div class="image-holder">
          <img :src="book.imageUrl" :alt="book.name">
        </div>

        <div class="text-holder">
          {{ book.description }}
        </div>
      </md-card-content>

      <md-card-actions>
        <md-button class="md-primary" v-if="enableProcess" @click="toStarted(book)">{{ processText }}</md-button>
        <md-button class="md-accent" v-if="enableDelete" @click="removeFromTodo(book)">Delete</md-button>
      </md-card-actions>

    </md-ripple>
  </md-card>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'

  export default {
    name: 'book',

    props: {
      book: {
        type: Object,
        required: true
      },
      processText: {
        type: String,
        required: true
      },
      processUrl: {
        type: String,
        required: true
      },
      enableProcess: {
        type: Boolean,
        required: false
      },
      enableDelete: {
        type: Boolean,
        required: false
      },
      deleteUrl: {
        type: String,
        required: false
      }
    },

    methods: {
      toStarted(book) {
        const axiosConfig = {
          headers: {
            'authorization': getToken()
          }
        }

        axios.post(this.processUrl, book, axiosConfig)
          .then(() => this.$emit('processed', book))
      },

      removeFromTodo(book) {
        axios({
          method: 'delete',
          url: this.deleteUrl,
          params: {
            bookId: book.id
          },
          headers: {
            'authorization': getToken()
          }
        })
          .then((res) => this.$emit('removed', book))
          .catch((err) => console.error(err))
      }
    },

    data () {
      return {
        showSearchDialog: false,

        searchCriteria: '',
        msg: 'Welcome to Your Vue.js App',
        books: []
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

</style>

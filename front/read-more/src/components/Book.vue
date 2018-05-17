<template>
  <md-card>
    <md-ripple>
      <md-card-header>
        <div class="md-title">{{ book.name }}</div>
        <div class="md-subhead">{{ book.author }}</div>
        <div class="md-subhead" v-if="book.id">Average rating: {{ book.averageRating }}</div>
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
        <md-button class="md-primary" v-if="state" @click="tweet">
          <md-icon>
            <svg style="width:24px;height:24px" viewBox="0 0 24 24">
              <path fill="#448aff" d="M22.46,6C21.69,6.35 20.86,6.58 20,6.69C20.88,6.16 21.56,5.32 21.88,4.31C21.05,4.81 20.13,5.16 19.16,5.36C18.37,4.5 17.26,4 16,4C13.65,4 11.73,5.92 11.73,8.29C11.73,8.63 11.77,8.96 11.84,9.27C8.28,9.09 5.11,7.38 3,4.79C2.63,5.42 2.42,6.16 2.42,6.94C2.42,8.43 3.17,9.75 4.33,10.5C3.62,10.5 2.96,10.3 2.38,10C2.38,10 2.38,10 2.38,10.03C2.38,12.11 3.86,13.85 5.82,14.24C5.46,14.34 5.08,14.39 4.69,14.39C4.42,14.39 4.15,14.36 3.89,14.31C4.43,16 6,17.26 7.89,17.29C6.43,18.45 4.58,19.13 2.56,19.13C2.22,19.13 1.88,19.11 1.54,19.07C3.44,20.29 5.7,21 8.12,21C16,21 20.33,14.46 20.33,8.79C20.33,8.6 20.33,8.42 20.32,8.23C21.16,7.63 21.88,6.87 22.46,6Z" />
            </svg>
          </md-icon>
          Tweet it!
        </md-button>
        <md-button class="md-primary" v-if="book.id" @click="showComments = true">Comments({{book.reviewsLength}})</md-button>
        <md-button class="md-primary" v-if="book.id && state" @click="showExtended = true">Show extended</md-button>
        <md-button class="md-primary" v-if="enableProcess" @click="toStarted(book)">{{ processText }}</md-button>
        <md-button class="md-accent" v-if="enableDelete" @click="removeFromTodo(book)">Delete</md-button>
      </md-card-actions>

    </md-ripple>

    <md-dialog :md-active.sync="showExtended">
      <book-ext :book="book"/>
    </md-dialog>

    <md-dialog :md-active.sync="showComments">
      <comment-list :book="book" @commentRemoved="onCommentRemoved"/>
    </md-dialog>

    <md-snackbar md-position="center" md-duration="2000" :md-active.sync="showSnackbar" md-persistent>
      <span>Tweeted!</span>
    </md-snackbar>
  </md-card>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import BookExt from "./BookExtended.vue";
  import CommentList from "./Comments.vue";

  export default {
    components: {
      CommentList,
      BookExt
    },

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
      },
      state: {
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
          .then((res) => this.$emit('processed', book, res.data))
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
      },

      tweet() {
        axios.post('/twitter/tweet/book?state=' + this.state + '&bookId=' + this.book.id)
          .then(() => this.showSnackbar = true)
      },

      onCommentRemoved() {
        this.book.reviewsLength--
      }
    },

    data () {
      return {
        showSnackbar: false,
        showExtended: false,
        showComments: false
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
</style>

<template>
  <div class="hello">

    <div class="image-container">
    </div>

    <div class="right-image">
    </div>

    <div class="left-image">
    </div>

    <div class="container">

      <div class="items">
        <book v-for="book in books"
              :book="book"
              :enableDelete="true"
              :enableProcess="true"
              deleteUrl="/book/state/"
              processText="Start"
              processUrl="/book/state/progress"
              state="TODO"
              @processed="onProcessed"
              @removed="onRemoved"/>
      </div>

      <md-button class="md-fab md-primary add-todo" @click="showSearchDialog = true">
        <md-icon>add</md-icon>
      </md-button>

      <md-button class="md-fab md-primary tweet-list" @click="tweetList">
        <md-icon>
          <svg style="width:24px;height:24px" viewBox="0 0 24 24">
            <path fill="#000000" d="M22.46,6C21.69,6.35 20.86,6.58 20,6.69C20.88,6.16 21.56,5.32 21.88,4.31C21.05,4.81 20.13,5.16 19.16,5.36C18.37,4.5 17.26,4 16,4C13.65,4 11.73,5.92 11.73,8.29C11.73,8.63 11.77,8.96 11.84,9.27C8.28,9.09 5.11,7.38 3,4.79C2.63,5.42 2.42,6.16 2.42,6.94C2.42,8.43 3.17,9.75 4.33,10.5C3.62,10.5 2.96,10.3 2.38,10C2.38,10 2.38,10 2.38,10.03C2.38,12.11 3.86,13.85 5.82,14.24C5.46,14.34 5.08,14.39 4.69,14.39C4.42,14.39 4.15,14.36 3.89,14.31C4.43,16 6,17.26 7.89,17.29C6.43,18.45 4.58,19.13 2.56,19.13C2.22,19.13 1.88,19.11 1.54,19.07C3.44,20.29 5.7,21 8.12,21C16,21 20.33,14.46 20.33,8.79C20.33,8.6 20.33,8.42 20.32,8.23C21.16,7.63 21.88,6.87 22.46,6Z" />
          </svg>
        </md-icon>
      </md-button>
    </div>

    <md-dialog :md-active.sync="showSearchDialog">
      <book-search @added="onAdded" @clicked="onCloseSearchModal"/>
    </md-dialog>

    <md-snackbar md-position="center" md-duration="2000" :md-active.sync="showSnackbar" md-persistent>
      <span>Tweeted!</span>
    </md-snackbar>

  </div>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import BookSearch from "./BookSearch.vue";
  import Book from "./Book.vue";

  export default {
    components: {
      Book,
      BookSearch
    },

    name: 'userTodos',

    mounted() {
      this.update()
    },

    methods: {
      update() {
        axios.get(
          '/book/state/todo',
          {
            headers: {
              'authorization': getToken()
            }
          })
          .then((res) => {
            this.books = res.data
          })
          .catch((err) => console.error(err))
      },

      onCloseSearchModal() {
        this.showSearchDialog = false;
      },

      onAdded(book) {
        this.books.push(book)
        this.showSearchDialog = false;
        this.$forceUpdate()
      },

      onRemoved(book) {
        console.log("removed")
        this.books = this.books.filter((b) => b !== book)
      },

      onProcessed(book) {
        this.onRemoved(book)
        this.$emit('action', '')
      },

      tweetList() {
        axios.post('/twitter/tweet/list?state=TODO')
          .then(() => this.showSnackbar = true)
          .catch((err) => console.error(err))
      }
    },

    data () {
      return {
        showSearchDialog: false,
        showSnackbar: false,
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

  .hello {
    position: absolute;
    top: 0;
    z-index: -1;
    width: 100%;
    min-height: 100vh;
    overflow: hidden;
  }

  .image-container {
    position: absolute;
    top: 0;
    left: 0;
    min-height: 100vh;
    width: 100%;
    text-align: center;
    background: linear-gradient(171deg,#7966f3,#a851e2);
  }

  .right-image {
    transform: skewX(-33deg) translateX(100%);
    width: 56%;
    min-height: 100vh;
    background: linear-gradient(-171deg,#9b9bfd,#7966f3);
    position: absolute;
  }

  .left-image {
    background: linear-gradient(-171deg,#17bebb,#9b9bfd);
    width: 56%;
    min-height: 100vh;
    position: absolute;
    transform: skewX(37deg) translateX(-29%);
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

  h1 {
    position: relative !important;
    top: 0 !important;
  }

  .md-progress-bar {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
  }

  .items {
    overflow: auto;
    max-height: 70vh;
    margin-top: 73px;
  }

  .add-todo {
    position: absolute;
    bottom: 20px;
    right: 20px;
  }

  .tweet-list {
    position: absolute;
    bottom: 100px;
    right: 20px;
  }

  .md-dialog {
    width: 80%;
  }
</style>

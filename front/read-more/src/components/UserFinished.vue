<template>
  <div class="hello">

    <div class="image-container">
    </div>

    <div class="right-image">
    </div>

    <div class="left-image">
    </div>

    <div class="container">

      <h1 class="todos">User finished</h1>

      <div class="items">
        <book v-for="book in books"
              :book="book"
              :enableDelete="true"
              :enableProcess="false"
              deleteUrl="http://localhost:8080/book/state/"
              processText="Start"
              processUrl="http://localhost:8080/book/state/finished"
              @processed="onRemoved"
              @removed="onRemoved"/>
      </div>

      <!--<md-button class="md-fab md-primary add-todo" @click="showSearchDialog = true">-->
        <!--<md-icon>add</md-icon>-->
      <!--</md-button>-->
    </div>

    <!--<md-dialog :md-active.sync="showSearchDialog">-->
      <!--<book-search @added="onAdded" @clicked="onCloseSearchModal"/>-->
    <!--</md-dialog>-->


  </div>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import BookSearch from "./BookSearch.vue";
  import Book from "./Book.vue";

  export default {
    components: {BookSearch, Book},
    name: 'userProgress',

    mounted() {
      axios.get(
        'http://localhost:8080/book/state/finished',
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

    methods: {
      onRemoved(book) {
        this.books = this.books.filter((b) => b !== book)
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
  }

  .add-todo {
    position: absolute;
    bottom: 20px;
    right: 20px;
  }
</style>

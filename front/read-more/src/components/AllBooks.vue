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
              :enableProcess="false"
              deleteUrl="/book"
              processText=""
              processUrl=""
              @removed="onRemoved"/>
      </div>

      <md-button class="md-fab md-primary add-todo" @click="showAddDialog = true">
        <md-icon>add</md-icon>
      </md-button>

    </div>

    <md-dialog :md-active.sync="showAddDialog" class="add-dialog-holder">
      <add-book @added="onAdded" @clicked="onCloseAddModal"/>
    </md-dialog>

  </div>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import Book from "./Book.vue"
  import AddBook from './AddBook.vue'

  export default {
    components: {
      Book,
      AddBook
    },

    name: 'all-books',

    mounted() {
      this.update()
    },

    methods: {
      update() {
        axios.get('/book/all')
          .then((res) => {
            this.books = res.data
          })
          .catch((err) => console.error(err))
      },

      onCloseSearchModal() {
        this.showSearchDialog = false;
      },

      onAdded(book) {
        this.books.unshift(book)
        this.showSearchDialog = false;
        this.$forceUpdate()
      },

      onRemoved(book) {
        console.log("removed")
        this.books = this.books.filter((b) => b !== book)
      }
    },

    data () {
      return {
        showAddDialog: false,
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

  .add-dialog-holder {
    width: 700px !important;
  }
</style>

<template>

  <div class="search-holder" style="text-align: center;">
    <md-dialog-title>Google Books</md-dialog-title>

    <div class="form-holder md-layout md-gutter">
      <md-field class="inputs md-layout-item md-small-size-100" md-clearable>
        <label>Book Name</label>
        <md-input v-model="bookName"></md-input>
      </md-field>

      <md-field class="inputs md-layout-item md-small-size-100">
        <label>Author</label>
        <md-input v-model="author"></md-input>
      </md-field>
    </div>

    <div class="items">
      <book v-for="book in books"
            :book="book"
            :enableDelete="false"
            :enableProcess="true"
            processText="TODO"
            processUrl="http://localhost:8080/book/state/todo"
            @processed="add"/>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised login" @click="searchBooks">Find!</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import Book from "./Book.vue";

  export default {
    components: {Book},
    name: 'book-search',

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      searchBooks() {
        console.log("search")

        axios.get(
          'http://localhost:8080/book?name=' + this.bookName + '&author=' + this.author,
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

      add(book) {
        const axiosConfig = {
          headers: {
            'authorization': getToken()
          }
        }

        axios.post('http://localhost:8080/book/state/todo', book, axiosConfig)
        .then((res) => {
          this.books = this.books.filter((b) => b !== book)
          this.$emit('added', book)
        })
      }
    },

    data() {
      return {
        bookName: '',
        author: '',
        books: []
      }
    }
  }
</script>

<style>
  .search-holder {
    width: 800px;
    padding-left: 50px;
    padding-right: 50px;
    padding-bottom: 30px;
    padding-top: 40px;
    max-height: 80vh;
  }

  .inputs {
    margin-left: 10px;
    margin-right: 10px;
  }

  .items {
    overflow: auto;
    max-height: 50vh;
  }
</style>

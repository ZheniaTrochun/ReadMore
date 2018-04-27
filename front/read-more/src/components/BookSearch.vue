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
      <md-card v-for="book in books">
        <md-ripple>
          <md-card-header>
            <div class="md-title">{{ book.name }}</div>
            <div class="md-subhead">{{ book.author }}</div>
          </md-card-header>

          <md-card-content>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio itaque ea, nostrum odio. Dolores, sed accusantium quasi non.
          </md-card-content>

          <md-card-actions>
            <md-button class="md-primary">Start</md-button>
            <md-button class="md-accent">Delete</md-button>
          </md-card-actions>

        </md-ripple>
      </md-card>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised login" @click="searchBooks">Find!</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  import { getToken } from '../auth/auth'

  export default {
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
    max-height: 70vh;
    /*width: 100%;*/
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

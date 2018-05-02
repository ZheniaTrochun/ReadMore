<template>

  <div class="book-ext-holder" style="text-align: center;">

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

    <div class="form-holder md-layout">
      <md-field>
        <label>Notes</label>
        <md-textarea rows="10" v-model="notes"></md-textarea>
        <md-icon>description</md-icon>
      </md-field>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised register" @click="saveNotes()">Save!</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  import { getToken } from '../auth/auth'
  import Book from "./Book.vue";

  export default {
    components: {Book},
    name: 'book-ext',

    props: {
      book: {
        type: Object,
        required: true
      }
    },

    mounted() {
      axios.get(
        'http://localhost:8080/book/state/notes',
        {
          headers: {
            'authorization': getToken()
          },
          params: {
            'bookId': this.book.id
          }
        })
        .then((res) => {
          this.notes = res.data.notes
        })
        .catch((err) => console.error(err))
    },

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      saveNotes() {
        axios({
          method: 'put',
          url: 'http://localhost:8080/book/state/notes',
          data: {
            bookId: this.book.id,
            notes: this.notes
          },
          headers: {
            'authorization': getToken()
          }
        })
          .then((res) => console.log("saved successfully!"))
          .catch((err) => alert(err))

      }
    },

    data() {
      return {
        notes: ''
      }
    }
  }
</script>

<style scoped>
  .book-ext-holder {
    width: 100%;
    padding-left: 50px;
    padding-right: 50px;
    padding-bottom: 30px;
    padding-top: 40px;
    max-height: 80vh;
    overflow: auto;
  }
</style>

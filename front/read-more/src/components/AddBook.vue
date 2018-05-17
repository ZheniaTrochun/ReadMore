<template>

  <div class="add-holder" style="text-align: center;">
    <md-dialog-title>Add Book</md-dialog-title>

    <div class="form-holder add-book-holder">
      <md-field md-clearable>
        <label>Book name</label>
        <md-input v-model="name"></md-input>
      </md-field>

      <md-field md-clearable>
        <label>Author</label>
        <md-input v-model="author"></md-input>
      </md-field>

      <md-field md-clearable>
        <label>Year</label>
        <md-input v-model="year"></md-input>
      </md-field>

      <md-field md-clearable>
        <label>Genre</label>
        <md-input v-model="genre"></md-input>
      </md-field>

      <md-field md-clearable>
        <label>Description</label>
        <md-textarea v-model="description"></md-textarea>
        <md-icon>description</md-icon>
      </md-field>

      <md-field md-clearable>
        <label>Image Link</label>
        <md-input v-model="image"></md-input>
      </md-field>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised login" @click="add">Add</md-button>
      <md-button class="md-raised md-accent" @click="closeModal">Close</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  import { storeToken } from '../auth/auth'

  export default {
    name: 'add-book',

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      add() {
        console.log("add")
        axios.post(
          '/book',
          JSON.stringify({
            name: this.name,
            author: this.author,
            genre: this.genre,
            year: this.year,
            description: this.description,
            imageUrl: this.image,
          }),
          {
            headers: {
              'Content-Type': 'application/json'
            }
          })
          .then((res) => {
            this.$emit('added', res.data)
          })
          .catch((err) => {
            this.$emit('clicked', '')
          });
      }
    },

    data() {
      return {
        name: '',
        author: '',
        year: '',
        description: '',
        image: '',
        genre: '',

        success: false,
        failure: false,
        failMessage: ''
      }
    }
  }
</script>

<style>
  .add-holder {
    width: 700px !important;
    padding-left: 50px;
    padding-right: 50px;
    padding-bottom: 30px;
    padding-top: 40px;
  }

  .add-book-holder {
    width: 600px !important;
  }
</style>

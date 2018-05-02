<template>
  <md-card>
    <md-card-content>

      <h4>{{ comment.username }}</h4>
      <h4>{{ comment.rating }}</h4>
      <h5>{{ comment.description }}</h5>

    </md-card-content>
  </md-card>
</template>

<script>

  import axios from 'axios'

  import { getToken } from '../auth/auth'

  export default {
    name: 'comment',

    props: {
      comment: {
        type: Object,
        required: true
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

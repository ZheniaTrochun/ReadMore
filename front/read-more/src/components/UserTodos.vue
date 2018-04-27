<template>
  <div class="hello">

    <div class="image-container">
    </div>

    <div class="right-image">
    </div>

    <div class="left-image">
    </div>

    <div class="container">

      <h1 class="todos">User TODOs</h1>

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

      <md-button class="md-fab md-primary add-todo">
        <md-icon>add</md-icon>
      </md-button>
    </div>


  </div>
</template>

<script>

  export default {
    name: 'userTodos',
    data () {
      return {
        searchCriteria: '',
        msg: 'Welcome to Your Vue.js App',
        books: [
          {
            id: 1,
            name: 'Harry Potter',
            author: 'J. K. Rowling',
          },
          {
            id: 1,
            name: 'Harry Potter 2',
            author: 'J. K. Rowling',
          },
          {
            id: 1,
            name: 'Harry Potter 3',
            author: 'J. K. Rowling',
          },
          {
            id: 1,
            name: 'Harry Potter',
            author: 'J. K. Rowling',
          },
          {
            id: 1,
            name: 'Harry Potter 2',
            author: 'J. K. Rowling',
          },
          {
            id: 1,
            name: 'Harry Potter 3',
            author: 'J. K. Rowling',
          }
        ]
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

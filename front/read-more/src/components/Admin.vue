<template>
  <div>

    <div class="head-block">
      <div class="header-holder">
        <div class="header-background">
        </div>

        <img class="icon" src="../assets/logo.png">

        <h1>Admin Dashboard</h1>

        <div class="top-buttons">
          <p class="username" v-if="isLoggedIn">{{ username }}</p>
          <md-button class="md-primary md-raised" v-if="isLoggedIn" @click="logout">Logout</md-button>
          <md-button class="md-primary login" v-if="!isLoggedIn" @click="showLoginDialog = true">Login</md-button>
          <md-button class="md-primary register" v-if="isLoggedIn" @click="showRegisterDialog = true">Register new</md-button>
        </div>

      </div>
    </div>

    <all-books v-if="isLoggedIn"/>

    <!--<router-view/>-->

    <md-dialog :md-active.sync="showRegisterDialog">
      <register @clicked="onCloseRegisterModal"/>
    </md-dialog>

    <md-dialog :md-active.sync="showLoginDialog">
      <login @clicked="onCloseLoginModal"/>
    </md-dialog>

    <md-dialog-alert
      :md-active.sync="success"
      :md-title="successHeader"
      :md-content="successMessage" />

    <md-dialog-alert
      :md-active.sync="failure"
      :md-title="failHeader"
      :md-content="failMessage" />

  </div>
</template>

<script>
  import Register from './Register.vue'
  import Login from './Login.vue'
  import AllBooks from './AllBooks.vue'
  import { deleteToken, isLoggedIn } from '../auth/auth'
  import axios from 'axios'

  export default {
    components: {
      Login,
      Register,
      AllBooks
    },

    name: 'Admin',

    data: () => {
      return {
        showRegisterDialog: false,
        showLoginDialog: false,

        success: false,
        successHeader: '',
        successMessage: '',

        failure: false,
        failHeader: '',
        failMessage: '',

        isLoggedIn: false,
        username: ''
      }
    },

    mounted() {
      this.isLoggedIn = isLoggedIn()

      axios.get('/user/username').then((res) => {
        this.username = res.data.username
        this.isLoggedIn = true
      }).catch((err) => {
        console.error(err)
        this.isLoggedIn = false
      })
    },

    methods: {

      onCloseModal() {
        this.showSignInDialog = false
      },

      onCloseLoginModal({res, msg}) {
        this.showLoginDialog = false

        if (res) {

          this.success = true
          this.successHeader = 'Login successful!'
          this.successMessage = 'You logged in successfully! \\o/'
          this.isLoggedIn = isLoggedIn()
        } else {

          this.failure = true
          this.failHeader = 'Login failed! ;('
          this.failMessage = msg
        }
      },

      onCloseRegisterModal({res, msg}) {
        this.showRegisterDialog = false

        if (res) {

          this.success = true
          this.successHeader = 'Register successful!'
          this.successMessage = 'You have been registered successfully! \\o/'
        } else {

          this.failure = true
          this.failHeader = 'Register failed! ;('
          this.failMessage = msg
        }
      },

      logout() {
        this.isLoggedIn = false
        axios.get('/user/logout')
          .then(() => window.location = '/')
      }
    }
  }
</script>

<style>
  body {
    z-index: -2;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  .username {
    display: inline-block;
    color: white;
    font-size: 19px;
    z-index: 1000;
    position: relative;
    margin-top: 15px;
  }

  .head-menu {
    width: 100% !important;
  }

  .head-block {
    min-height: 100px;
    width: 100%;
    position: fixed;
    top: 0;
  }

  .top-buttons {
    margin-top: 28px;
    float: right;
    margin-right: 30px;
  }

  .header-holder {
    position: relative;
    height: 100px;
    background: linear-gradient(180deg,#333 0,#383838 50%,#333);
  }

  .header-background {
    background: linear-gradient(270deg,#7966f3 0,#a851e2);
    height: 100px;
    width: 80%;
    position: absolute;
    left: -60px;
    transform: skewX(-50deg) translateX(0);
  }

  .md-tab {
    height: 100vh !important;
    width: 100%;
    flex: 1 0 100%;
    position: relative;
    top: -170px;
    z-index: -1000;
    padding: 0 !important;
  }

  .icon {
    height: 57px;
    width: 57px;
    position: absolute;
    left: 100px;
    top: 20px;
  }

  h1 {
    display: block;
    width: 100%;
    text-align: center;

    color: white;
    font-size: 40px;
    font-weight: lighter;

    position: absolute;
    top: 15px;
  }

  .menu-icon {
    float: right;
    margin-right: 50px;
    margin-top: 30px;
  }

  .login {
    color: white !important;
    background: linear-gradient(171deg,#17bebb,#1ad5d1) !important;
  }

  .register {
    color: white !important;
    background: linear-gradient(171deg,#a851e2,#b367e6) !important;
  }
</style>

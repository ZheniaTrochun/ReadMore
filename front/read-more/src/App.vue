<template>
  <div id="app">

    <div class="head-block">
      <div class="header-holder">
        <div class="header-background">
        </div>

        <img class="icon" src="./assets/logo.png">

        <h1>Read More! ;)</h1>

        <md-button class="md-icon-button menu-icon" @click="navigation = !navigation">
          <md-icon>menu</md-icon>
        </md-button>

        <div class="top-buttons">
          <md-button class="login md-raised" v-if="!isLoggedIn" @click="showLoginDialog = true">Login</md-button>
          <md-button class="register md-raised" v-if="!isLoggedIn" @click="showRegisterDialog = true">Register</md-button>
          <md-button class="md-primary md-raised" v-if="isLoggedIn" @click="logout">Logout</md-button>
        </div>

      </div>

      <md-tabs v-if="navigation" class="teal" md-alignment="centered" md-sync-route>
        <md-tab id="tab-home" md-label="Home" md-icon="home" to="/"></md-tab>
        <md-tab id="tab-todo" v-if="isLoggedIn" md-label="Todo" md-icon="favorite" to="/todo"></md-tab>
        <md-tab id="tab-progress" v-if="isLoggedIn" md-label="In Progress" md-icon="pages" to="/components/tabs/home"></md-tab>
        <md-tab id="tab-finished" v-if="isLoggedIn" md-label="Finished" md-icon="pages" to="/components/tabs/home"></md-tab>
      </md-tabs>
    </div>

    <router-view/>

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
  import Register from './components/Register.vue'
  import Login from './components/Login.vue'
  import { deleteToken, isLoggedIn } from './auth/auth'

  export default {
    components: {
      Login,
      Register
    },

    name: 'App',

    data: () => {
      return {
        navigation: false,
        showRegisterDialog: false,
        showLoginDialog: false,

        success: false,
        successHeader: '',
        successMessage: '',

        failure: false,
        failHeader: '',
        failMessage: '',

        isLoggedIn: false
      }
    },

    mounted() {
      this.isLoggedIn = isLoggedIn()
    },

    methods: {
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
        deleteToken()
        this.isLoggedIn = false
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
    background: linear-gradient(171deg,#17bebb,#1ad5d1);
  }

  .register {
    background: linear-gradient(171deg,#a851e2,#b367e6);
  }
</style>

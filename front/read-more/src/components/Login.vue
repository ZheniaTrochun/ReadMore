<template>

  <div class="register-holder" style="text-align: center;">
    <md-dialog-title>Login</md-dialog-title>

    <div class="form-holder">
      <md-field md-clearable>
        <label>Username</label>
        <md-input v-model="username"></md-input>
      </md-field>

      <md-field>
        <label>Password</label>
        <md-input v-model="password" type="password"></md-input>
      </md-field>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised login" @click="login">Login</md-button>
      <md-button class="md-raised md-accent" @click="closeModal">Close</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  export default {
    name: 'login',

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      login() {
        console.log("login")
        axios.post(
          'http://localhost:8080/user/login',
          JSON.stringify({
            username: this.username,
            password: this.password
          }))
          .then(() => {
            this.$emit('clicked',
              {
                res: true,
                msg: ''
              }
            )
          })
          .catch((err) => {
            this.$emit('clicked',
              {
                res: false,
                msg: 'Invalid username or password!'
              }
            )
          });
      }
    },

    data() {
      return {
        username: '',
        password: '',

        success: false,
        failure: false,
        failMessage: ''
      }
    }
  }
</script>

<style>
  .register-holder {
    width: 500px;
    padding-left: 50px;
    padding-right: 50px;
    padding-bottom: 30px;
    padding-top: 40px;
  }
</style>

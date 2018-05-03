<template>

  <div class="register-holder" style="text-align: center;">
    <md-dialog-title>Register</md-dialog-title>

    <div class="form-holder">
      <md-field md-clearable>
        <label>Username</label>
        <md-input v-model="username"></md-input>
      </md-field>

      <md-field>
        <label>Password</label>
        <md-input v-model="password" type="password"></md-input>
      </md-field>

      <md-field :md-toggle-password="false">
        <label>Email</label>
        <md-input v-model="email" type="email"></md-input>
      </md-field>
    </div>

    <md-dialog-actions>
      <md-button class="md-raised register" @click="register">Register</md-button>
      <md-button class="md-raised md-accent" @click="closeModal">Close</md-button>
    </md-dialog-actions>

  </div>

</template>

<script>
  import axios from 'axios'

  export default {
    name: 'register',

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      register() {
        axios.post(
          'http://localhost:8080/user/register',
          {
            username: this.username,
            password: this.password,
            email: this.email
          })
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
                msg: err.response.data.message
              }
            )
          });
      }
    },

    data() {
      return {
        username: '',
        password: '',
        email: '',

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

<template>

  <div class="register-holder" style="text-align: center;">
    <md-dialog-title>Sign In</md-dialog-title>

    <!-- <md-dialog-actions> -->
      <div class="">

        <div class="md-layout md-gutter md-alignment-center">
          <div class="md-layout-item  md-xsmall-size-100">
            <form action="/signin/twitter" method="post">
              <md-button class="md-raised md-primary" type="submit">
                <md-icon class="md-size-2x">
                  <svg style="width:24px;height:24px" viewBox="0 0 24 24">
                    <path fill="#000000" d="M22.46,6C21.69,6.35 20.86,6.58 20,6.69C20.88,6.16 21.56,5.32 21.88,4.31C21.05,4.81 20.13,5.16 19.16,5.36C18.37,4.5 17.26,4 16,4C13.65,4 11.73,5.92 11.73,8.29C11.73,8.63 11.77,8.96 11.84,9.27C8.28,9.09 5.11,7.38 3,4.79C2.63,5.42 2.42,6.16 2.42,6.94C2.42,8.43 3.17,9.75 4.33,10.5C3.62,10.5 2.96,10.3 2.38,10C2.38,10 2.38,10 2.38,10.03C2.38,12.11 3.86,13.85 5.82,14.24C5.46,14.34 5.08,14.39 4.69,14.39C4.42,14.39 4.15,14.36 3.89,14.31C4.43,16 6,17.26 7.89,17.29C6.43,18.45 4.58,19.13 2.56,19.13C2.22,19.13 1.88,19.11 1.54,19.07C3.44,20.29 5.7,21 8.12,21C16,21 20.33,14.46 20.33,8.79C20.33,8.6 20.33,8.42 20.32,8.23C21.16,7.63 21.88,6.87 22.46,6Z" />
                  </svg>
                </md-icon>
                Sign in with Twitter
              </md-button>
            </form>
          </div>
        </div>

        <div class="md-layout md-gutter md-alignment-center">
          <div class="md-layout-item  md-xsmall-size-100">
            <form class="">
              <md-button class="md-raised login" @click="email">Sign in with email</md-button>
            </form>
          </div>
        </div>

        <div class="md-layout md-gutter md-alignment-center">
          <div class="md-layout-item  md-xsmall-size-100">
            <form class="">
              <md-button class="md-raised md-accent" @click="closeModal">Close</md-button>
            </form>
          </div>
        </div>
      </div>
    <!-- </md-dialog-actions> -->
  </div>

</template>

<script>
  import axios from 'axios'

  import { storeToken } from '../auth/auth'

  export default {
    name: 'signin',

    methods: {
      closeModal() {
        this.$emit('clicked', '')
      },

      email() {
        this.$emit('email', '')
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

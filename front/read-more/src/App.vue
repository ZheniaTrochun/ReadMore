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
          <md-button class="login md-raised">Login</md-button>
          <md-button class="register md-raised" @click="showRegisterDialog = true">Register</md-button>
        </div>

      </div>

      <md-tabs v-if="navigation" class="teal" md-alignment="centered" md-sync-route>
        <md-tab id="tab-home" md-label="Home" md-icon="home" to="/"></md-tab>
        <md-tab id="tab-todo" md-label="Todo" md-icon="favorite" to="/components/tabs/home"></md-tab>
        <md-tab id="tab-progress" md-label="In Progress" md-icon="pages" to="/components/tabs/home"></md-tab>
        <md-tab id="tab-finished" md-label="Finished" md-icon="pages" to="/components/tabs/home"></md-tab>
      </md-tabs>
    </div>

    <router-view/>

    <md-dialog :md-active.sync="showRegisterDialog">
      <md-dialog-title style="text-align: center;">Register</md-dialog-title>

      <div class="form-holder">
        <md-field md-clearable>
          <label>Cleareable</label>
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
        <md-button class="md-raised register" @click="showRegisterDialog = false">Register</md-button>
        <md-button class="md-raised md-accent" @click="showRegisterDialog = false">Close</md-button>
      </md-dialog-actions>
    </md-dialog>


  </div>
</template>

<script>
export default {
  name: 'App',
  data: function() {
    return {
      navigation: false,
      showRegisterDialog: false
    }
  }
}
</script>

<style>
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
  color: white;
  font-size: 40px;
  font-weight: lighter;

  position: absolute;
  left: 30%;
  top: 15px;
}

.menu-icon {
  /* position: absolute;
  right: 50px;
  top: 30px; */
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

.md-dialog {
  width: 500px;
  padding-left: 50px;
  padding-right: 50px;
  padding-bottom: 30px;
}

/* .form-holder {
  padding-left: 50px;
  padding-right: 50px;
} */
</style>

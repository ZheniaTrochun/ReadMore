<template>
  <div class="hello">

    <div class="image-container">
    </div>

    <div class="right-image">
    </div>

    <div class="left-image">
    </div>

  </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style>
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
</style>

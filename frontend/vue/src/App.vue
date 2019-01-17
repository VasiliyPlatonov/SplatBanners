<template>
  <v-app style="background-color: #fff !important;">
    <drawer></drawer>
    <toolbar></toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
  import Drawer from "./components/Drawer.vue"
  import Toolbar from "./components/Toolbar.vue"

  export default {
    data() {
      return {
        drawerImageSrc: './img/sidebar-2.jpg',
        responsive: false,
        logo: './img/logo.png',
        links: [
          {title: 'Login', icon: 'lock', url: '/login'},
        ]
      }
    },
    components: {
      drawer: Drawer,
      toolbar: Toolbar
    },
  }
</script>

<style scoped>


  .pointer {
    cursor: pointer;
  }
</style>

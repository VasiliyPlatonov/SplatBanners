<template>
  <v-toolbar
    :clipped-left="$vuetify.breakpoint.lgAndUp"
    color="blue darken-1"
    dark
    app
    fixed
  >
    <v-toolbar-title class="ml-0 pl-3 toolbar__title">
      <v-toolbar-side-icon
        @click.stop="toggleDrawer"></v-toolbar-side-icon>
      <span class="hidden-sm-and-down">Banners</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn icon
           v-for="link in links"
           :key="link.title"
           :to="link.url"
    >
      <v-icon>{{ link.icon }}</v-icon>
    </v-btn>
  </v-toolbar>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    data() {
      return {
        links: [
          {title: 'Login', icon: 'lock', url: '/login'},
          {title: 'Registration', icon: 'face', url: '/registration'},
        ]
      }
    },
    methods: {
      ...mapActions({
        toggleDrawer: 'toggleShown'
      }),
      checkDrawer(){
        var isShown = this.$store.getters.isShown;
        console.log(isShown)
      }
    },
    computed: {
      drawerShown() {
        return this.$store.getters.isShown;
      }
    }

  }
</script>

<style scoped>
  .toolbar__search {
    width: 80px;
  }

  .toolbar__title {
    width: 300px;
  }
</style>

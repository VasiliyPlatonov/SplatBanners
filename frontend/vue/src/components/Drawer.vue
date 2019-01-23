<template>
  <v-navigation-drawer
    :clipped="$vuetify.breakpoint.lgAndUp"
    :value="drawer"
    fixed
    app
  >
    <v-list>
      <v-list-tile
        v-for="link in links"
        :key="link.title"
        :to="link.url"
      >
        <v-list-tile-action>
          <v-icon>{{ link.icon }}</v-icon>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-list-tile-title v-text="link.title"></v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  import {mapMutations} from 'vuex'

  export default {
    data() {
      return {
        links: [
          {title: 'Banners', icon: 'view_comfy', url: '/banners', active: false},
          {title: 'Login', icon: 'lock', url: '/login', active: false},
        ]
      }
    },
    computed: {
      drawer() {
        return this.$store.getters.isShown
      }
    },
    methods: {
      setShown(val) {
        this.$store.commit('setShown', val)
      }
    },
    mounted() {
      this.setShown(window.innerWidth > 1024)
    }


  }
</script>

<style scoped>
  .darken {
    background-color: rgba(27, 27, 27, .84)
  }
</style>

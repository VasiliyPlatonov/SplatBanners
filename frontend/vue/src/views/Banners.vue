<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex xs12>
          <h1 class="text--secondary mb-3">Banners</h1>
        </v-flex>
      </v-layout>
    </v-container>

    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex xs12 sm3 lg2
                v-for="banner in banners"
                :key="banner.id"
        >
          <banner :banner="banner"></banner>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
  import banner from '../components/BannerCard.vue'

  export default {
    computed: {
      banners: {
        get() {
          return this.$store.getters.getBanners
        }
      },
    },
    mounted() {
      this.banners = this.$store.dispatch('setBanners')
    },
    components: {
      banner
    }
  }
</script>




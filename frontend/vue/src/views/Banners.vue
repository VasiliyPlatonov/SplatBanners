<template>
  <div>
    <v-container>
      <v-layout row>
        <v-flex xs6>
          <h1 class="text--secondary mb-3">Banners</h1>
        </v-flex>
        <v-flex xs6>
          <banner-tools></banner-tools>
        </v-flex>
      </v-layout>
    </v-container>

    <v-container grid-list-md>
      <v-layout row wrap
                v-if="!$store.getters.getTableView">
        <v-flex xs12 sm3 lg2
                v-for="banner in banners"
                :key="banner.id"
        >
          <banner-card
            :banner="banner"></banner-card>
        </v-flex>
      </v-layout>
      <banner-table v-else
                    :banners="banners"
                    ></banner-table>
    </v-container>
  </div>
</template>
<script>
  import BannerCard from '../components/BannerCard.vue'
  import BannerTools from '../components/BannerTools.vue'
  import BannerTable from '../components/BannerTable.vue'

  export default {
    computed: {
      banners() {
        return this.$store.getters.getBanners
      },
    },
    mounted() {
      this.banners = this.$store.dispatch('setBanners')
    },
    components: {
      bannerCard: BannerCard,
      bannerTools: BannerTools,
      bannerTable: BannerTable
    }
  }
</script>




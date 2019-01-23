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
        <v-flex xs12 sm6 md4 lg3
                v-for="banner in banners"
                :key="banner.id"
        >
          <v-card class="mb-3">
            <a :href="banner.targetUrl">
              <v-img
                :src="banner.imgSrc"
                aspect-ratio="1.7"
                contain

              ></v-img>
            </a>
            <v-card-title primary-title>
              <h3 class="headline mb-0">Id: {{ banner.id }}</h3>
              <p>Width: {{ banner.width }}</p>
              <p>Height: {{ banner.height }}</p>
              <span>Target url:</span>
              <a :href="banner.targetUrl">
                {{ banner.targetUrl }}
              </a>
              <p>Banner langId: {{ banner.langId }}</p>
            </v-card-title>

            <v-card-actions>
              <v-btn flat color="orange">Share</v-btn>
              <v-btn flat color="orange">Explore</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
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
  }
</script>


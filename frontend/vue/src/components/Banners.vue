<template>
  <div>
    <button @click="getBanners">Get me the banners</button>
    <ul>
      <li v-for="banner in banners">{{ banner.targetUrl }}</li>
    </ul>

  </div>
</template>

<script>
  import axios from "axios"

  export default {
    name: 'Banners',
    data: () => ({
      banners: [],
      errors: []
    }),
    methods: {
      getBanners() {
        axios.get('/banner').then(response => {
          this.banners = response.data
        })
          .catch(e => {
            this.errors.push(e)
          })
      }
    }
  }
</script>

<style>

</style>

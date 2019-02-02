<template>

  <v-layout row align-center justify-end>

    <v-btn icon>
      <v-icon icon>search</v-icon>
    </v-btn>

    <banner-create-modal>
      <v-btn icon>
        <v-icon icon>add</v-icon>
      </v-btn>
    </banner-create-modal>
    <v-btn icon
           @click.stop="toggleTableView">
      <v-icon icon>{{ viewIcon }}</v-icon>
    </v-btn>

  </v-layout>

</template>

<script>
  import BannerCreateModal from './BannerCreateModal.vue'

  export default {
    computed: {
      tableView() {
        return this.$store.getters.getTableView
      },
      viewIcon() {
        if (this.tableView) {
          return 'view_comfy'
        }
        else return 'view_headline'
      }
    },
    methods: {
      toggleTableView() {
        this.$store.dispatch('toggleTableView')
      }
    },
    components: {
      bannerCreateModal: BannerCreateModal
    }
  }
</script>

<style lang="less">

</style>

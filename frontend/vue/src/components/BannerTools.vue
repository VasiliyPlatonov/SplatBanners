<template>

  <v-layout row align-center justify-end>
    <v-btn icon>
      <v-icon icon>search</v-icon>
    </v-btn>

    <v-btn icon
           @click.stop="toggleTableView">
      <v-icon icon>{{ viewIcon }}</v-icon>
    </v-btn>
  </v-layout>

</template>

<script>
  export default {
    computed: {
      tableView() {
        return this.$store.getters.getTableView
      },
      viewIcon() {
        if (!this.tableView) {
          return 'view_comfy'
        }
        else return 'view_list'
      }
    },
    methods: {
      toggleTableView() {
        this.$store.dispatch('toggleTableView')
      }
    }

  }
</script>

<style lang="less">

</style>

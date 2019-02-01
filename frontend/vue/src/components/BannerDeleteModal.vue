<template>
  <v-dialog
    v-model="deleteModal"
    maxWidth="600px"
  >
    <slot slot="activator"></slot>
    <v-card>
      <v-card-title class="headline">
        Do you really want to remove the banner with ID {{ id }}?
      </v-card-title>

      <v-card-text>
        All data about this banner will be permanently deleted.
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn
          flat="flat"
          @click="deleteModal = false"
        >
          Disagree
        </v-btn>

        <v-btn
          color="error"
          flat="flat"
          @click="deleteItem()"
        >
          Agree
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
  export default {
    props: ['banner'],
    data() {
      return {
        deleteModal: false,
        id: this.banner.id
      }
    },
    methods: {
      deleteItem() {
        this.$store.dispatch('deleteBanner', this.banner)
        this.deleteModal = false
      }
    }
  }
</script>

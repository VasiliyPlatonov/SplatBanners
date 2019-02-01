<template>
  <v-data-table
    :headers="headers"
    :items="banners"
    class="banner-table elevation-4"
    hide-actions
  >
    <template slot="items" slot-scope="banners">
      <td>{{ banners.item.id }}</td>
      <td class="text-xs-right">{{ banners.item.imgSrc }}</td>
      <td class="text-xs-right">{{ banners.item.width }}</td>
      <td class="text-xs-right">{{ banners.item.height }}</td>
      <td class="text-xs-right">{{ banners.item.targetUrl }}</td>
      <td class="text-xs-right">{{ banners.item.langId }}</td>
      <td class="align-center justify-center layout px-0">

        <banner-edit-modal :banner="banners.item">
          <v-icon small class="mr-2">edit</v-icon>
        </banner-edit-modal>

        <banner-delete-modal :banner="banners.item">
          <v-icon small>delete</v-icon>
        </banner-delete-modal>

      </td>
    </template>
  </v-data-table>
</template>


<script>
  import BannerEditModal from './BannerEditModal.vue'
  import BannerDeleteModal from './BannerDeleteModal.vue'

  export default {
    props: ['banners'],
    data() {
      return {
        headers: [
          {text: 'Id', value: 'id'},
          {text: 'Image source', value: 'imgSrc'},
          {text: 'Width', value: 'width'},
          {text: 'Height', value: 'height'},
          {text: 'Target URL', value: 'targetUrl'},
          {text: 'Language id', value: 'langId'},
          {text: 'Actions', value: 'name', sortable: false}
        ],
      }
    },
    components: {
      bannerEditModal: BannerEditModal,
      bannerDeleteModal: BannerDeleteModal
    }
  }
</script>

<style lang="less">
  .banner-table {
    td {
      word-wrap: normal;
      word-break: break-all;
      max-width: 100px;
    }
  }
</style>

<template>
  <v-hover>
    <v-card
      slot-scope="{ hover }"
      :class="`elevation-${hover ? 12 : 3}`"
      class="banner-card"
    >
      <v-img
        :src="banner.imgSrc"
        aspect-ratio="0.9"
        cover
      >
        <v-expand-transition>
          <div v-show="hover"
            class="transition-fast-in-fast-out darken-1 banner-card--reveal"
            style="height: 100%;"
          >
            <banner-edit-modal :banner="banner">
              <v-btn class="banner-card__btn" color="blue darken-1" fab small dark>
                <v-icon>edit</v-icon>
              </v-btn>
            </banner-edit-modal>

            <banner-delete-modal :banner="banner">
              <v-btn class="banner-card__btn" color="blue darken-1" fab small dark>
                <v-icon>delete</v-icon>
              </v-btn>
            </banner-delete-modal>
          </div>
        </v-expand-transition>
      </v-img>


      <v-card-title>
        <div class="banner-card__title-wrap">
          <h3 class="headline">Id: {{ banner.id }}</h3>
          <p>Width: {{ banner.width }}</p>
          <p>Height: {{ banner.height }}</p>
          <p>Target url: <a class="banner-card__targetUrl" :href="banner.targetUrl">
            {{ banner.targetUrl }}
          </a>
          </p>
          <p>Banner langId: {{ banner.langId }}</p>
        </div>
      </v-card-title>

    </v-card>
  </v-hover>
</template>

<script>
  import BannerEditModal from './BannerEditModal.vue'
  import BannerDeleteModal from './BannerDeleteModal.vue'

  export default {
    props: ['banner'],
    components: {
      bannerEditModal: BannerEditModal,
      bannerDeleteModal: BannerDeleteModal
    }
  }
</script>

<style lang="less">
  .banner-card {
    margin-bottom: 15px;
    margin-right: 5px;
    margin-left: 5px;

    p {
      margin-bottom: 5px;
    }

    &__targetUrl {
      max-width: 100%;
      display: inline-block;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    &__title-wrap {
      max-width: 100%;
    }
    &--reveal {
      display: flex;
      bottom: 0;
      justify-content: space-between;
      position: absolute;
      width: 100%;
    }
  }

</style>

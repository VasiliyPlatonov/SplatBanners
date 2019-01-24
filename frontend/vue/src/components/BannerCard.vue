<template>
    <v-hover>
      <v-card
        slot-scope="{ hover }"
        :class="`elevation-${hover ? 10 : 3}`"
        class="banner"
      >

        <a :href="banner.targetUrl">
          <v-img
            :src="banner.imgSrc"
            aspect-ratio="0.9"
            cover
          ></v-img>
        </a>

        <v-card-title>
          <div class="banner__title-wrap">
            <h3 class="headline">Id: {{ banner.id }}</h3>
            <p>Width: {{ banner.width }}</p>
            <p>Height: {{ banner.height }}</p>
            <p>Target url:
              <a class="banner__targetUrl" :href="banner.targetUrl">
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
  export default {
    props: ['banner']
  }
</script>

<style lang="less">
  .banner {
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
  }
</style>

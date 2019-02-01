<template>

  <v-dialog v-model="editModal" maxWidth="600px">
    <slot slot="activator"></slot>

    <v-card>
      <v-card-title>
        <h2 class="text--primary">Edit banner</h2>
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-container grid-list-md>
          <v-layout wrap row>

            <v-flex xs4>
              <v-text-field
                v-model="width"
                label="Width*"
                required
              ></v-text-field>
            </v-flex>

            <v-flex xs4>
              <v-text-field
                v-model="height"
                label="Height*"
                required
              >
              </v-text-field>
            </v-flex>

            <v-flex xs4>
              <v-select
                :items="languages"
                v-model="langId"
                label="language*"
              >
              </v-select>
            </v-flex>

            <v-flex xs12>
              <v-text-field
                v-model="imgSrc"
                label="Image source*"
                required
              >
              </v-text-field>
            </v-flex>

            <v-flex xs12>
              <v-text-field
                v-model="targetUrl"
                label="Target URL*"
                required
              >
              </v-text-field>
            </v-flex>

          </v-layout>
        </v-container>
        <small>*indicates required field</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" flat @click="onClose">Close</v-btn>
        <v-btn color="blue darken-1" flat @click="onSave">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

</template>
<script>
  export default {
    props: ['banner'],
    data() {
      return {
        editModal: false,
        width: this.banner.width,
        height: this.banner.height,
        langId: this.banner.langId,
        imgSrc: this.banner.imgSrc,
        targetUrl: this.banner.targetUrl,
        languages: ['ru', 'en', 'de']
      }
    },
    methods: {
      onClose() {
        this.width = this.banner.width
        this.height = this.banner.height
        this.langId = this.banner.langId
        this.imgSrc = this.banner.imgSrc
        this.targetUrl = this.banner.targetUrl
        this.editModal = false
      },
      onSave() {
        /*todo: It is necessary to make validation here*/
        this.$store.dispatch('updateBanner', {
          id: this.banner.id,
          width: this.width,
          height: this.height,
          langId: this.langId,
          imgSrc: this.imgSrc,
          targetUrl: this.targetUrl
        })
        this.editModal = false
      }
    }
  }
</script>

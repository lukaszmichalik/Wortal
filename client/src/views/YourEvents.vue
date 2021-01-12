<template>
  <v-app class="global_app">
    <v-card
      v-for="{ address, city, surface, id } in events"
      :key="id"
      class="mx-auto ma-5"
      max-width="60%"
      min-width="60%"
      padding="20px"
    >
      <v-img :src="getImgUrl(surface)" v-bind:alt="surface" height="200px"> </v-img>

      <v-card-title v-if="loaded">
        {{ city }}
      </v-card-title>

      <v-card-subtitle>
        {{ address }}
      </v-card-subtitle>

      <v-card-actions>
        <v-btn color="orange lighten-2" text @click="enterEventInfo(id)">
          Przeglądaj
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>
            I'm a thing. But, like most politicians, he promised more than he
            could deliver. You won't have time for sleeping, soldier, not with
            all the bed making you'll be doing. Then we'll go with that data
            file! Hey, you add a one and two zeros to that or we walk! You're
            going to do his laundry? I've got to find a way to escape.
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-app>
</template>


<script>
import UserService from '../services/user.service';
import EventService from '../services/event.service';

export default {
  name: 'YourEvents',
  data() {
    return {
      userValue: this.$store.state.auth.user || '',
      events: '',
      loaded: false,
      show: false,
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
    validateUrl() {
      return 'validating';
    },
  },
  methods: {
    enterEventInfo(id) {
      EventService.getEvent(id);
      this.$router.push('/eventOverview');
    },
    getImgUrl(surface) {
      var images = require.context('../assets/', false)
      switch (surface) {
        case 'Hala':
          return images('./' + surface + ".jpg")
        case 'Naturalna':
          return images('./' + surface + ".jpg")
        case 'Sztuczna':
          return images('./' + surface + ".jpg")
        case 'Tartan':
          return images('./' + surface + ".png")
        default:
          console.log(`Sorry, we are out of ${surface}.`);
      }
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    UserService.getUserEvents(this.currentUser.id).then((data) => {
      this.events = data;
      this.loaded = true;
      console.log(this.events);
    });
  },
};
//UserService.getEvents(this.userValue.id)
//{{events[0].city}}
</script>
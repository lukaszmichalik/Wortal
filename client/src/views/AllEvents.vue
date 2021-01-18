<template>
  <v-app class="global_app">
    <p class="mx-auto mt-5 white--text display-3">Wszystkie Wydarzenia</p>
    <v-card
      v-for="event in events"
      :key="event.id"
      class="mx-auto ma-5"
      max-width="50%"
      min-width="50%"
      padding="20px"
    >
      <v-img
        :src="getImgUrl(event.surface)"
        v-bind:alt="event.surface"
        height="200px"
        aspect-ratio="2.75"
      >
        <v-card-title class="white--text mt-3">
          <p class="ml-3">
            {{ formatDate(event.date) }}
          </p>
        </v-card-title>

        <p class="white--text ml-8 display-3">{{ event.time }}</p>
      </v-img>

      
        <v-card-title v-if="loaded" class="ml-1">
          {{ event.city }}
        </v-card-title>

        <v-card-subtitle v-if="loaded" class="ml-1">
          {{ event.address }}
        </v-card-subtitle>

      <v-card-text class="ml-1">
         limit graczy: {{ event.limitation }}
          <v-icon color="green" small> mdi-account </v-icon>
      </v-card-text>

      

      <v-card-actions>
        <v-btn color="primary lighten-2" :loading="loading && selectedBtn==event.id" text @click="enterEventInfo(event.id)">
          Przeglądaj
          <v-icon color="primary" small>mdi-information-outline</v-icon>
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn icon @click="selected(event.id)">
          <v-icon>{{
            selectedCards.includes(event.id)
              ? 'mdi-chevron-up'
              : 'mdi-chevron-down'
          }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-if="selectedCards.includes(event.id)">
          <v-divider></v-divider>

          <v-card-text>
            {{ event.description }}
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
  name: 'AllEvents',
  data() {
    return {
      userValue: this.$store.state.auth.user || '',
      events: '',
      loaded: false,
      loading: false,
      show: false,
      selectedCards: [],
      selectedBtn: ''
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
    selected(id) {
      if (this.selectedCards.includes(id)) {
        let index = this.selectedCards.indexOf(id);
        if (index >= 0) {
          this.selectedCards.splice(index, 1);
        }
      } else {
        this.selectedCards.push(id);
      }
    },
    enterEventInfo(id) {
      this.selectedBtn=id
      EventService.getEvent(id);
      var that=this;
      this.loading=true;
      setTimeout(function () {
      
      that.$router.push('/eventOverview');
       }, 500);
    },
    getImgUrl(surface) {
      var images = require.context('../assets/', false);
      switch (surface) {
        case 'hala':
          return images('./' + surface + '.jpg');
        case 'naturalna':
          return images('./' + surface + '.jpg');
        case 'sztuczna':
          return images('./' + surface + '.jpg');
        case 'tartan':
          return images('./' + surface + '.png');
        default:
          console.log(`Sorry, we are out of ${surface}.`);
      }
    },
    formatDate(date) {
      var expectedDateFormat = new Date(date);
      // var dateString = expectedDateFormat.toString();
      var expectedMonths = [
        'Styczeń',
        'Luty',
        'Marzec',
        'Kwiecień',
        'Maj',
        'Czerwiec',
        'Lipiec',
        'Sierpień',
        'Wrzesień',
        'Październik',
        'Listopad',
        'Grudzień',
      ][expectedDateFormat.getMonth()];

      var expectedDateString =
        expectedDateFormat.getDay() +
        ' ' +
        expectedMonths +
        ' ' +
        expectedDateFormat.getFullYear();
      return expectedDateString;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    EventService.notAttendedEvents(this.currentUser.id).then((data) => {
      this.events = data;
      this.loaded = true;
    });
  },
};
</script>

<style>
</style>

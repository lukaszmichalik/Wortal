<template>
  <v-app class="global_app">
    <p id="your_events_caption" class="global_caption">Twoje wydarzenia</p>

    <v-card
      id="event_card"
      v-for="event in events"
      :key="event.id"
      class="mx-auto ma-5"
    >
      <v-img
        :src="getImg(event.surface)"
        v-bind:alt="event.surface"
        height="200px"
        aspect-ratio="2.75"
      >
        <v-card-title class="global_event_card_date">
          <p>
            {{ formatDate(event.date) }}
          </p>
        </v-card-title>

        <p class="global_event_card_day_of_week">
          {{ getDayOfWeek(event.date) }}
        </p>

        <p class="global_event_card_time">{{ event.time }}</p>
      </v-img>

      <v-card-title v-if="loaded">
        <v-icon color="green" medium> mdi-map-marker </v-icon>
        {{ event.city }}
      </v-card-title>

      <v-card-subtitle v-if="loaded" class="global_event_card_address">
        {{ event.address }}
      </v-card-subtitle>

      <v-card-text>
        <v-icon color="green" medium> mdi-account </v-icon>
        limit uczestników: {{ event.limitation }}
      </v-card-text>

      <v-card-actions>
        <v-btn
          class="global_event_card_button"
          :loading="loading"
          @click="enterEventInfo(event.id)"
        >
          PRZEGLĄDAJ
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn v-if="event.description != ''" icon @click="selected(event.id)">
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
import EventService from '../services/event.service';
import DateFormatter from '../utils/dateFormatter';
import LoadSurfaceImg from '../utils/loadSurfaceImg';

export default {
  name: 'YourEvents',
  data() {
    return {
      userValue: this.$store.state.auth.user || '',
      events: '',
      loaded: false,
      loading: false,
      show: false,
      selectedCards: [],
      selectedBtn: '',
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
    getDayOfWeek(date) {
      return DateFormatter.getDayOfWeek(date);
    },
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
      this.selectedBtn = id;
      EventService.getEvent(id);
      var that = this;
      this.loading = true;
      setTimeout(function () {
        that.$router.push('/eventOverview');
      }, 500);
    },
    getImg(surface) {
      return LoadSurfaceImg.getImg(surface);
    },
    formatDate(date) {
      return DateFormatter.formatDate(date);
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else {
      EventService.getUserEvents(this.currentUser.id).then((data) => {
        this.events = data;
        this.loaded = true;
      });
    }
  },
};
</script>





<style>
@import '../styles/style_global.css';
@import '../styles/style_your_events.css';
</style>

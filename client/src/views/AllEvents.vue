<template>
  <v-app class="global_app">
    <p id="all_events_caption" class="global_caption">Wszystkie wydarzenia</p>

    <v-col justify="center" align="center" margin-bottom="0px">
      <v-autocomplete
        class="global_search"
        v-model="searchCity"
        :items="myJson"
        label="wyszukaj według miejscowości"
        filled
        hide-details
        no-data-text="brak podanej miejscowości"
      />
    </v-col>

    <!--<div class="global_div_centerize">
      <p class="global_no_events_label">brak wydarzeń</p>
    </div>!-->

    <v-col
      id="all_events_list"
      name="global_events_list"
      class="global_events_list"
      justify="center"
      align="center"
    >
      <div v-for="event in events" :key="event.id">
        <v-card
          class="global_event_card"
          v-if="event.city == searchCity || !searchCity"
        >
          <!-- v-if="event.city == searchCity || !searchCity" !-->
          <v-img
            :src="getImgUrl(event.surface)"
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

          <v-card-title>
            <v-icon color="green" medium> mdi-map-marker </v-icon>
            {{ event.city }}
          </v-card-title>

          <v-card-subtitle class="global_event_card_address">
            {{ event.address }}
          </v-card-subtitle>

          <v-card-text>
            <v-icon color="green" medium> mdi-account </v-icon>
            limit uczestników: {{ event.limitation }}
          </v-card-text>

          <v-card-actions>
            <v-btn
              class="global_event_card_button"
              :loading="loading && selectedBtn == event.id"
              @click="enterEventInfo(event.id)"
            >
              PRZEGLĄDAJ
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
              v-if="event.description != ''"
              icon
              @click="selected(event.id)"
            >
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
      </div>
    </v-col>
  </v-app>
</template>


<script>
import EventService from '../services/event.service';
import json from '../resources/miasta.json';
import DateFormatter from '../utils/dateFormatter';
import LoadSurfaceImg from '../utils/loadSurfaceImg';

export default {
  name: 'AllEvents',
  data() {
    return {
      myJson: json,
      userValue: this.$store.state.auth.user || '',
      events: '',
      loading: false,
      show: false,
      selectedCards: [],
      selectedBtn: '',
      searchCity: '',
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
      this.selectedBtn = id;
      EventService.getEvent(id);
      var that = this;
      this.loading = true;
      setTimeout(function () {
        that.$router.push('/eventOverview');
      }, 500);
    },
    getImgUrl(surface) {
      return LoadSurfaceImg.getImgUrl(surface);
    },
    getDayOfWeek(date) {
      var eventDate = new Date(date);
      var daysOfWeek = [
        'niedziela',
        'poniedziałek',
        'wtorek',
        'środa',
        'czwartek',
        'piątek',
        'sobota',
      ][eventDate.getDay()];
      return daysOfWeek;
    },
    formatDate(date) {
      return DateFormatter.formatDate(date);
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else {
      EventService.notAttendedEvents(this.currentUser.id).then((data) => {
        this.events = data;
      });
    }
  },
};
</script>

<style>
@import '../styles/style_global.css';
@import '../styles/style_all_events.css';
</style>
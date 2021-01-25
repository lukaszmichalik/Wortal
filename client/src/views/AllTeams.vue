<template>
  <v-app class="global_app">
    <p id="all_teams_caption" class="global_caption">Wszystkie drużyny</p>

    <div class="mx-auto ma-5">
      <v-autocomplete
        v-model="searchCity"
        :items="myJson"
        label="wyszukaj według miejscowości"
        filled
        hide-details
        background-color="white"
        no-data-text="brak podanej miejscowości"
      />
    </div>

    <div v-for="team in teams" :key="team.id">
      <v-card
        id="global_team_card"
        v-if="team.location == searchCity || !searchCity"
        class="mx-auto ma-5"
      >
        <v-card-title v-if="loaded" class="global_team_card_name">
          {{ team.name }}
        </v-card-title>

        <v-card-subtitle v-if="loaded" class="global_team_card_city">
          <v-icon color="green" medium> mdi-map-marker </v-icon>
          {{ team.location }}
        </v-card-subtitle>

        <v-card-actions>
          <v-btn
            class="global_event_card_button"
            :loading="loading && selectedBtn == team.id"
            @click="enterTeamInfo(team.id)"
          >
            PRZEGLĄDAJ
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn v-if="team.description != ''" icon @click="selected(team.id)">
            <v-icon>{{
              selectedCards.includes(team.id)
                ? 'mdi-chevron-up'
                : 'mdi-chevron-down'
            }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-if="selectedCards.includes(team.id)">
            <v-divider></v-divider>

            <v-card-text>
              {{ team.description }}
            </v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
    </div>
  </v-app>
</template>





<script>
import TeamService from '../services/team.service';
import json from '../resources/miasta.json';

export default {
  name: 'AllTeams',
  data() {
    return {
      userValue: this.$store.state.auth.user || '',
      teams: '',
      loaded: false,
      loading: false,
      show: false,
      selectedCards: [],
      selectedBtn: '',
      myJson: json,
      searchCity: '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
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
    enterTeamInfo(id) {
      this.selectedBtn = id;
      TeamService.getTeam(id);
      var that = this;
      this.loading = true;
      setTimeout(function () {
        that.$router.push('/teamOverview');
      }, 500);
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    TeamService.allTeams(this.currentUser.id).then((data) => {
      this.teams = data;
      this.loaded = true;
    });
  },
};
</script>





<style>
@import '../styles/style_global.css';
@import '../styles/style_all_teams.css';
</style>

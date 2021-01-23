<template>
  <v-app class="global_app">
    <p class="mx-auto mt-5 white--text display-3">Twoje Drużyny</p>
    <v-card
      v-for="team in teams"
      :key="team.id"
      class="mx-auto ma-5"
      max-width="50%"
      min-width="50%"
      padding="20px"
    >
        <v-card-title v-if="loaded" class="ml-1 display-1">
          {{ team.name }}
        </v-card-title>
  
        <v-card-subtitle v-if="loaded" class="ml-1 display-1">
          {{ team.location }}
        </v-card-subtitle>

      <v-card-actions>
        <v-btn color="primary lighten-2" :loading="loading && selectedBtn==team.id" text @click="enterTeamInfo(team.id)">
          Przeglądaj
          <v-icon color="primary" small>mdi-information-outline</v-icon>
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn icon @click="selected(team.id)">
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
  </v-app>
</template>


<script>
import TeamService from '../services/team.service';

export default {
  name: 'YourTeams',
  data() {
    return {
      userValue: this.$store.state.auth.user || '',
      teams: '',
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
      this.selectedBtn=id
      TeamService.getTeam(id);
      var that=this;
      this.loading=true;
      setTimeout(function () {
      
      that.$router.push('/teamOverview');
       }, 500);
    },
    
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    TeamService.getUserTeams(this.currentUser.id).then((data) => {
      this.teams = data;
      this.loaded = true;
    });

  },
};
</script>

<style>
</style>

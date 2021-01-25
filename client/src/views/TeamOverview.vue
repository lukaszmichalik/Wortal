<template>
  <v-app class="global_app">
    <p id="team_overview_caption" class="global_caption">{{currentTeam.name}}</p>
    
    <v-card
      class="mx-auto ma-5"
      width="80vw"
      padding="20px"
      elevation="12"
    >
      <v-card-title class="global_overview_label"
        ><v-icon color="green" large class="global_overview_icon">mdi-shield</v-icon>
        nazwa drużyny:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.name"></v-card-text>

      <v-card-title class="global_overview_label"
        ><v-icon color="green" large class="global_overview_icon">mdi-map-marker</v-icon
        >miasto:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.location"></v-card-text>

      <v-card-title class="global_overview_label"
        ><v-icon color="green" large class="global_overview_icon">mdi-calendar-question</v-icon>data
        utworzenia:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="formatDate(currentTeam.creationDate)"
      ></v-card-text>

      <v-card-title class="global_overview_label"
        ><v-icon color="green" large class="global_overview_icon">mdi-account-group</v-icon>liczba
        członków:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.players.length"></v-card-text>

      <v-card-title class="global_overview_label"
        ><v-icon color="green" large class="global_overview_icon">mdi-information</v-icon>informacje:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.description"></v-card-text>

      <v-card-actions>
        <v-btn
          v-if="!isManager && hasAccess"
          class="mx-auto"
          :color="playersIds.includes(currentUser.id) ? 'error' : 'green'"
          :loading="loading"
          large
          text
          @click="joinOrGiveUp()"
        >
          {{ playersIds.includes(currentUser.id) ? 'OPUŚĆ' : 'DOŁĄCZ' }}
          <v-icon
            :color="playersIds.includes(currentUser.id) ? 'error' : 'green'"
            right
            >{{
              playersIds.includes(currentUser.id)
                ? 'mdi-account-minus'
                : 'mdi-account-plus'
            }}</v-icon
          >
        </v-btn>
      </v-card-actions>
    </v-card>

    <p id="team_overview_lower_caption" class="global_caption">Członkowie</p>

    <v-card
      id="team_overview_participant"
      class="mx-auto ma-1"
      v-for="player in currentTeam.players"
      :key="player.id"
      elevation="12"
    >
      <v-row>
        <!--<v-col class="hidden-sm-and-down" align="center">
          <v-avatar color="indigo ma-5" size="50">
            <span class="white--text headline">{{
              getInitials(player.name)
            }}</span>
          </v-avatar>
        </v-col>!-->

        <v-col class="text-no-wrap">
          <v-card-title>imię i nazwisko</v-card-title>

          <v-card-text v-text="player.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>pozycja</v-card-title>

          <v-card-text v-text="player.position"></v-card-text>
        </v-col>

        <v-col class="hidden-sm-and-down">
          <v-card-title>wiek</v-card-title>

          <v-card-text
            align="left"
            v-text="calculateAge(player.dob) + ' lat'"
          ></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-btn
            v-if="isManager && player.id != currentUser.id"
            color="error ma-8"
            :loading="loadingDelPlayer && selectedDelBtns.includes(player.id)"
            @click.once="deletePlayer(player.id)"
          >
            USUŃ
          </v-btn>
        </v-col>
      </v-row>
    </v-card>





    <p id="team_overview_lower_caption" class="global_caption">Manager</p>
    <v-card
      id="team_overview_participant"
      class="mx-auto ma-1"
      elevation="12"
    >
      <v-row>
        <v-col class="text-no-wrap">
          <v-card-title> imię i nazwisko:</v-card-title>

          <v-card-text v-text="currentTeam.manager.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title> kontakt: </v-card-title>

          <v-card-text v-text="currentTeam.manager.email"></v-card-text>
        </v-col>
      </v-row>
    </v-card>

    <p></p>


    <v-dialog transition="dialog-bottom-transition" max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-if="isManager"
          class="mx-auto my-8"
          color="error"
          v-bind="attrs"
          v-on="on"
          >ROZWIĄŻ DRUŻYNĘ</v-btn
        >
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <v-toolbar color="error" dark
            >Czy na pewno chcesz usunąć drużynę?</v-toolbar
          >
          <v-card-text>
            <div class="text-h5 pa-12">
              Tej akcji nie można cofnąć. Drużyna zostanie usunięta i wszyscy
              członkowie stracą do niej dostęp. Czy na pewno chcesz usunąć to
              wydarznie?
            </div>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn text @click="dialog.value = false">ANULUJ</v-btn>
            <v-btn text color="error" @click="deleteTeam(currentTeam.id)"
              >TAK, USUŃ DRUŻYNĘ</v-btn
            >
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
  </v-app>
</template>


 <script>
import TeamService from '../services/team.service';
import DateFormatter from '../utils/dateFormatter';
import CalculateAge from '../utils/calculateAge';

export default {
  name: 'TeamOverview',
  data() {
    return {
      teamValue: JSON.parse(localStorage.getItem('team')) || {},
      userValue: this.$store.state.auth.user || '',
      isManager: false,
      playersIds: [],
      loading: false,
      loadingDelPlayer: false,
      hasAccess: true,
      selectedDelBtns: [],
    };
  },
  computed: {
    currentTeam: {
      get: function () {
        return this.teamValue;
      },
      set: function (team) {
        this.teamValue = team;
        localStorage.setItem('team', JSON.stringify(team));
      },
    },
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    calculateAge(userBirthday) {
      return CalculateAge.calculateAge(userBirthday)
    },
    joinOrGiveUp() {
      var that = this;

      this.loading = true;

      if (!this.playersIds.includes(this.currentUser.id)) {
        TeamService.addUserToTeam(this.currentUser.id, this.currentTeam.id);

        setTimeout(function () {
          TeamService.getTeam(that.currentTeam.id);
        }, 600);

        setTimeout(function () {
          that.currentTeam = JSON.parse(localStorage.getItem('team'));
          that.playersIds.push(that.currentUser.id);
          that.loading = false;
        }, 1200);
      } else {
        TeamService.deleteUserFromTeam(
          this.currentUser.id,
          this.currentTeam.id
        );

        setTimeout(function () {
          TeamService.getTeam(that.currentTeam.id);
        }, 600);

        setTimeout(function () {
          that.currentTeam = JSON.parse(localStorage.getItem('team'));
          let index = that.playersIds.indexOf(that.currentUser.id);
          if (index >= 0) {
            that.playersIds.splice(index, 1);
          }
          that.loading = false;
        }, 1200);
      }
    },
    deletePlayer(playerId) {
      this.selectedDelBtns.push(playerId);
      this.loadingDelPlayer = true;
      var that = this;
      TeamService.deleteUserFromTeam(playerId, this.currentTeam.id);

      setTimeout(function () {
        TeamService.getTeam(that.currentTeam.id);
      }, 600);

      setTimeout(function () {
        that.currentTeam = JSON.parse(localStorage.getItem('team'));
        let index = that.playersIds.indexOf(playerId);
        if (index >= 0) {
          that.playersIds.splice(index, 1);
        }
        that.loadingDelPlayer = false;
      }, 1200);
    },

    deleteTeam(id) {
      TeamService.deleteTeam(id);
      this.$router.push('/yourTeams');
    },
    formatDate(date) {
     return DateFormatter.formatDate(date)
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    if (this.currentUser.id == this.teamValue.manager.id) {
      this.isManager = true;
    }

    for (let i = 0; i < this.currentTeam.players.length; i++) {
      this.playersIds.push(this.teamValue.players[i].id);
    }
  },
};
</script>





<style>
@import '../styles/style_global.css';
@import '../styles/style_team_overview.css';
</style>
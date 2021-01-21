<template>
  <v-app class="global_app">
    <p id="event_details_caption" class="global_caption">Szczegóły drużyny</p>
    <v-card
      class="mx-auto ma-5"
      max-width="80%"
      min-width="80%"
      padding="20px"
      elevation="12"
    >
      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-account-group</v-icon>
        nazwa:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.name"></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-map-marker</v-icon
        >miasto:</v-card-title
      >

      <v-card-text class="title" v-text="currentTeam.location"></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-calendar-question</v-icon>data
        utworzenia:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="currentTeam.creationDate"
      ></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-information</v-icon>informacje
        dla uczestników:</v-card-title
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
          {{ playersIds.includes(currentUser.id) ? 'zrezygnuj' : 'dołącz' }}
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

    <p id="event_details_caption" class="global_caption">Uczestnicy</p>
    <v-card
      class="mx-auto ma-1"
      min-width="80%"
      max-width="80%"
      padding="20px"
      v-for="player in currentTeam.players"
      :key="player.id"
      elevation="12"
    >
      <v-row>
        <v-col class="hidden-sm-and-down" align="center">
          <v-avatar color="indigo ma-5" size="50">
            <span class="white--text headline">{{
              getInitials(player.name)
            }}</span>
          </v-avatar>
        </v-col>

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
            large
            dark
            @click.once="deletePlayer(player.id)"
          >
            Usuń
          </v-btn>
        </v-col>
      </v-row>
    </v-card>

    <p id="event_details_caption" class="global_caption">Manager</p>
    <v-card
      class="mx-auto mb-12"
      min-width="80%"
      max-width="80%"
      padding="20px"
      elevation="12"
    >
      <v-row>
        <v-col class="text-no-wrap">
          <v-card-title> imię i nazwisko:</v-card-title>

          <v-card-text v-text="currentTeam.manager.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap flex-sm">
          <v-card-title> kontakt: </v-card-title>

          <v-card-text v-text="currentTeam.manager.email"></v-card-text>
        </v-col>
      </v-row>
    </v-card>
    <v-dialog transition="dialog-bottom-transition" max-width="600">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-if="isManager"
          class="mx-auto my-8"
          color="error"
          v-bind="attrs"
          v-on="on"
          >Rozwiąż drużyne</v-btn
        >
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <v-toolbar color="error" dark
            >Czy na pewno chcesz usunąć drużyne ?</v-toolbar
          >
          <v-card-text>
            <div class="text-h5 pa-12">
              Tej akcji nie można cofnąć, drużyna zostanie usunięta i wszyscy
              członkowie stracą do niej dostęp. Czy na pewno chcesz usunąć to
              wydarznie?
            </div>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn text @click="dialog.value = false">Anuluj</v-btn>
            <v-btn text color="error" @click="deleteTeam(currentTeam.id)"
              >Tak, Usuń</v-btn
            >
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
  </v-app>
</template>


 <script>
import UserService from '../services/user.service';
import TeamService from '../services/team.service';

export default {
  name: 'TeamOverview',
  data() {
    return {
      teamValue: JSON.parse(localStorage.getItem('team')) || {},
      userValue: JSON.parse(localStorage.getItem('user')) || {},
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
    calculateAge(birthday) {
      let currentDate = new Date();
      let birthDate = new Date(birthday);
      let difference = currentDate - birthDate;
      let age = Math.floor(difference / 31557600000);
      return age;
    },
    getInitials(name) {
      return name
        .split(' ')
        .map((n) => n[0])
        .join('');
    },
    joinOrGiveUp() {
      var that = this;

      this.loading = true;

      if (!this.playersIds.includes(this.currentUser.id)) {
        UserService.addUserToUser(this.currentUser.id, this.currentTeam.id);

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
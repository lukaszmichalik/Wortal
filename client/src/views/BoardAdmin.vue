<template>
  <v-app class="global_app">
    <p id="event_details_caption" class="global_caption">
      Użytkownicy aplikacji:
    </p>
    <div class="mx-auto ma-10">
      <v-autocomplete
        class="global_data_input"
        v-model="selectedName"
        :items="names"
        label="wyszukaj po imieniu i nazwisku"
        filled
        hide-details
        background-color="white"
        no-data-text="brak danych"
      />
    </div>
    <div v-for="user in users" :key="user.id">
      <v-card
        class="mx-auto ma-5"
        padding="20px"
        max-width="80%"
        v-if="
          (user.name == selectedName || !selectedName) &&
          user.name != currentUser.name
        "
        elevation="12"
      >
        <v-row>
          <v-col class="text-no-wrap">
            <v-card-title>imię i nazwisko</v-card-title>
            <v-card-text v-text="user.name"></v-card-text>
          </v-col>

          <v-col class="text-no-wrap">
            <v-card-title>pozycja</v-card-title>
            <v-card-text v-text="user.position"></v-card-text>
          </v-col>

          <v-col class="hidden-sm-and-down">
            <v-card-title>wiek</v-card-title>
            <v-card-text v-text="calculateAge(user.dob) + ' lat'"></v-card-text>
          </v-col>

          <v-col class="text-no-wrap">
            <v-dialog transition="dialog-bottom-transition" max-width="600">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  id="create_event_add_participant_button"
                  class="mx-auto my-8"
                  color="error"
                  v-bind="attrs"
                  v-on="on"
                  >USUŃ</v-btn
                >
              </template>
              <template v-slot:default="dialog">
                <v-card>
                  <v-toolbar color="error" dark
                    >Czy na pewno chcesz usunąć tego użytkownika ?</v-toolbar
                  >
                  <v-card-text>
                    <div class="text-h5 pa-12">
                      Tej akcji nie można cofnąć. Wraz z usunięciem użytkownika
                      usunięte zostaną wszystkie zarządzane przez niego drużyny
                      oraz organizowane wydarzania. Czy na pewno chcesz usunąć
                      to wydarznie?
                    </div>
                  </v-card-text>
                  <v-card-actions class="justify-end">
                    <v-btn text @click="dialog.value = false">Anuluj</v-btn>
                    <v-btn
                      :loading="loading"
                      text
                      color="error"
                      @click="deleteUser(user.id)"
                      >Tak, Usuń</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </template>
            </v-dialog>
          </v-col>
        </v-row>
      </v-card>
    </div>
    <div class="ma-10"></div>
  </v-app>
</template>





<script>
import UserService from '../services/user.service';
import TeamService from '../services/team.service';
import CalculateAge from '../utils/calculateAge';

export default {
  name: 'Admin',
  data() {
    return {
      users: '',
      names: [''],
      selectedName: '',
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      selectedUsers: [],
      loading: false,
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    calculateAge(userBirthday) {
      return CalculateAge.calculateAge(userBirthday);
    },

    deleteUser(userId) {
      var that = this;
      this.loading = true;

      var userManagedTeamsIds = [];

      setTimeout(function () {
        TeamService.getUserManagedTeams(userId).then((data) => {
          for (let i = 0; i < data.length; i++) {
            userManagedTeamsIds.push(data[i].id);
          }
        });
      }, 500);

      setTimeout(function () {
        for (let i = 0; i < userManagedTeamsIds.length; i++) {
          TeamService.deleteTeam(userManagedTeamsIds[i]);
        }
      }, 1000);

      setTimeout(function () {
        UserService.deleteUser(userId);
        that.loading = false;
        that.$router.go('/admin');
      }, 1500);
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else {
      if (!this.currentUser.roles.includes('ROLE_ADMIN')) {
        this.$router.push('/yourEvents');
      }

      UserService.allUsers().then((data) => {
        for (let i = 0; i < data.length; i++) {
          this.names.push(data[i].name);
        }
        this.users = data;
      });
    }
  },
};
</script>

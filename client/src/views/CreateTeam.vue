<template>
  <v-app id="create_team_app">
    <div id="create_team_div" class="global_div">
      <p id="create_team_caption" class="global_caption">Utwórz drużynę</p>

      <form @keypress.enter.prevent @submit.prevent="createTeam">
        <v-col id="create_team_col">
          <p id="create_team_label">nazwa drużyny:</p>

          <p class="create_team_input_required" v-if="!$v.team.name.required">
            to pole jest wymagane
          </p>
          <p class="create_team_input_required" v-if="!$v.team.name.maxLength">
            maksymalna ilość znaków to 50
          </p>

          <input
            id="create_team_address"
            class="global_data_input"
            type="text"
            v-model="team.name"
            placeholder="nazwa drużyny"
          />
        </v-col>

        <v-col id="create_team_col">
          <p id="create_team_label_city">miejscowość:</p>

          <p
            class="create_team_input_required"
            v-if="!$v.team.location.required"
          >
            to pole jest wymagane
          </p>

          <v-autocomplete
            id="create_team_city"
            class="global_data_input"
            v-model="team.location"
            :items="myJson"
            label="miejscowość"
            filled
            hide-details
            no-data-text="brak danych"
          />
        </v-col>

        <v-col id="create_team_col">
          <p id="create_team_label">opis drużyny:</p>

          <p
            class="create_team_input_required"
            v-if="!$v.team.description.maxLength"
          >
            maksymalna ilość znaków to 250
          </p>

          <textarea
            id="create_team_description"
            class="global_data_input"
            type="text"
            v-model="team.description"
          />
        </v-col>

        <!-- CARD USERS STARTS -->

        <p id="create_team_caption" class="global_caption">
          Dodaj uczestników:
        </p>

        <v-col id="create_team_participants_list">

        <div class="global_search" id="create_team_search">
          <v-autocomplete
            v-model="selectedName"
            :items="names"
            label="wyszukaj po imieniu i nazwisku"
            filled
            hide-details
            background-color="white"
            no-data-text="brak użytkowników"
          />
        </div>

<div id="create_team_list">
        <div
          v-for="user in users"
          :key="user.id"
        >
        <div>
          <v-card
            id="create_team_participant"
            padding="20px"
            v-if="(user.name == selectedName || !selectedName) && user.name!=currentUser.name"
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
                <!-- chowa wiek, gdy za mało miejsca !-->
                <v-card-title>wiek</v-card-title>
                <v-card-text
                  v-text="calculateAge(user.dob) + ' lat'"
                ></v-card-text>
              </v-col>

              <v-col class="text-no-wrap">
                <v-btn
                  :color="selectedUsers.includes(user.id) ? 'error' : 'green'"
                  @click="addTeammate(user.id)"
                  id="create_team_add_participant_button"
                >
                  {{ selectedUsers.includes(user.id) ? 'ANULUJ' : 'DODAJ' }}
                </v-btn>
              </v-col>
            </v-row>
          </v-card>
        </div>
        </div>
</div>
        </v-col>

        <!-- CARDS USERS ENDING -->

        <div class="global_div_centerize">
          <label
            id="create_team_error"
            class="global_error"
            v-if="creatingTeamFailed == 'input error'"
          >
            NIE UDAŁO SIĘ UTWORZYĆ WYDARZENIA. WYPEŁNIJ POPRAWNIE WSZYSTKIE POLA
            FORMULARZA.
          </label>
          <label
            id="create_team_error"
            class="global_error"
            v-if="creatingTeamFailed == 'creating team failed'"
          >
            NIE UDAŁO SIĘ UTWORZYĆ WYDARZENIA.
          </label>
        </div>

        <div id="create_team_buttons_div" class="global_div_centerize">
          <v-btn
            id="create_team_button_edit"
            class="global_v_btn"
            type="submit"
            >UTWÓRZ</v-btn
          >
          <br />
          <v-btn
            id="create_team_button_cancel"
            class="global_v_btn"
            to="/yourTeams"
            >ANULUJ</v-btn
          >
        </div>
      </form>
    </div>
  </v-app>
</template>


<script>
import UserService from '../services/user.service';
import TeamService from '../services/team.service';
import Team from '../models/team';
import json from '../resources/miasta.json';
import CalculateAge from '../utils/calculateAge';
import {
  required,
  maxLength,
} from 'vuelidate/lib/validators';


export default {
  name: 'CreateTeam',
  data() {
    return {
      myJson: json,
      message: '',
      team: new Team('', '', '', '', '', []),
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      users: '',
      selectedUsers: [],
      successful: false,
      creatingTeamFailed: '',
      names: [''],
      selectedName: '',
      loading: false
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  validations: {
    team: {
      name: {
        required,
        maxLength: maxLength(50),
      },
      location: {
        required,
      },
      description: {
        maxLength: maxLength(250),
      },
    },
  },
  methods: {
    createTeam() {

      this.loading = true
      var that = this;

      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        this.creatingTeamFailed = 'input error';
      } else {
        this.team.creationDate = Date.now();
        TeamService.createTeam(this.team).then(
          (this.successful = true),
          setTimeout(function () {
          that.$router.push('/yourTeams'),
          that.loading=false
          }, 500)
        );
      }
    },
    calculateAge(userBirthday) {
      return CalculateAge.calculateAge(userBirthday)
    },
    addTeammate(userId) {
      if (this.selectedUsers.includes(userId)) {
        let index = this.selectedUsers.indexOf(userId);
        if (index >= 0) {
          this.selectedUsers.splice(index, 1);
        }
        let inx = this.team.players.indexOf(userId);
        if (inx >= 0) {
          this.team.players.splice(inx, 1);
        }
      } else {
        this.selectedUsers.push(userId);
        this.team.players.push(userId);
      }
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    UserService.allUsers().then((data) => {
      for (let i = 0; i < data.length; i++) {
        this.names.push(data[i].name);
      }
      this.users = data;
    });

    this.team.manager_id = this.currentUser.id;

    this.selectedUsers.push(this.currentUser.id);
    this.team.players.push(this.currentUser.id);
  },
};
</script>





<style>
@import '../styles/style_create_team.css';
@import '../styles/style_global.css';
</style>
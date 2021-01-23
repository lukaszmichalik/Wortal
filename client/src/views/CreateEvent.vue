<template>
  <v-app id="create_event_app">
    <div id="create_event_div" class="global_div">
      <p id="ecreate_event_caption" class="global_caption">Utwórz wydarzenie</p>

      <form @keypress.enter.prevent @submit.prevent="handleCreateEvent">
        <v-col id="create_event_col">
          <p id="create_event_label_city">miejscowość:</p>

          <p class="create_event_input_required" v-if="!$v.event.city.required">
            to pole jest wymagane
          </p>

          <v-autocomplete
            id="create_event_city"
            class="global_data_input"
            v-model="event.city"
            :items="myJson"
            label="miejscowość"
            filled
            hide-details
            no-data-text="brak danych"
          />
        </v-col>

        <v-col id="create_event_col">
          <p id="create_event_label">adres:</p>

          <p
            class="create_event_input_required"
            v-if="!$v.event.address.required"
          >
            to pole jest wymagane
          </p>
          <p
            class="create_event_input_required"
            v-if="!$v.event.address.maxLength"
          >
            maksymalna ilość znaków to 50
          </p>

          <input
            id="create_event_address"
            class="global_data_input"
            type="text"
            v-model="event.address"
            placeholder="ul. Wolności 1"
          />
        </v-col>

        <v-col class="ml-5">
          <p id="create_event_label">data:</p>

          <p class="create_event_input_required" v-if="!$v.event.date.required">
            to pole jest wymagane
          </p>
          <v-date-picker
            :first-day-of-week="1"
            v-model="event.date"
            locale="pl-PL"
            :min="new Date().toISOString().substr(0, 10)"
          ></v-date-picker>
        </v-col>

        <v-col id="create_event_col">
          <p id="create_event_label">godzina rozpoczęcia:</p>

          <p class="create_event_input_required" v-if="!$v.event.time.required">
            to pole jest wymagane
          </p>

          <input
            id="create_event_time"
            class="global_data_input"
            type="time"
            v-model="event.time"
            format="24hr"
          />
        </v-col>

        <v-col id="create_event_col">
          <p id="create_event_label">nawierzchnia:</p>

          <p
            class="create_event_input_required"
            v-if="!$v.event.surface.required"
          >
            to pole jest wymagane
          </p>

          <select
            id="create_event_surface"
            class="global_data_input"
            v-model="event.surface"
          >
            <option disabled value="" selected hidden>
              rodzaj nawierzchni
            </option>

            <option value="hala">hala</option>
            <option value="sztuczna">sztuczna</option>
            <option value="naturalna">naturalna</option>
            <option value="tartan">tartan</option>
          </select>
        </v-col>

        <v-col id="create_event_col">
          <p id="create_event_label">limit uczestników:</p>

          <p
            class="create_event_input_required"
            v-if="!$v.event.limitation.required"
          >
            to pole jest wymagane
          </p>
          <p
            class="create_event_input_required"
            v-if="!$v.event.limitation.minValue"
          >
            minimalna ilość uczestników to 4
          </p>
          <p
            class="create_event_input_required"
            v-if="!$v.event.limitation.maxValue"
          >
            maksymalna ilość uczestników to 32
          </p>

          <v-text-field
            id="create_event_limitation"
            class="global_data_input"
            hide-details
            single-line
            type="number"
            v-model="event.limitation"
            max="32"
            min="4"
            placeholder="12"
          />
        </v-col>

        <v-col id="create_event_col">
          <p id="create_event_label">szczegóły:</p>

          <p
            class="create_event_input_required"
            v-if="!$v.event.description.maxLength"
          >
            maksymalna ilość znaków to 250
          </p>

          <textarea
            id="create_event_description"
            class="global_data_input"
            type="text"
            v-model="event.description"
          />
        </v-col>

        <!-- CARDS TEAMS STARTS-->

        <div v-if="userTeamsIds.length != 0">
          <p id="event_details_caption" class="global_caption">
            Dodaj drużyny:
          </p>

          <div
            id="create_event_participants_list"
            v-for="team in userTeamsWithPlayers"
            :key="team.name"
          >
            <div>
              <v-card
                id="create_event_participant"
                padding="20px"
                elevation="12"
              >
                <v-row>
                  <v-col class="text-no-wrap">
                    <v-card-title>nazwa drużyny</v-card-title>
                    <v-card-text v-text="team.name"></v-card-text>
                  </v-col>

                  <v-col class="text-no-wrap">
                    <v-card-title>liczba członków</v-card-title>
                    <v-card-text v-text="team.players.length"></v-card-text>
                  </v-col>

                  <v-col class="text-no-wrap">
                    <v-btn
                      :color="
                        selectedTeams.includes(team.id) ? 'error' : 'green'
                      "
                      @click="
                        selectedTeams.includes(team.id)
                          ? deleteTeamFromEvent(team.id, team.players)
                          : addTeamToEvent(team.id, team.players)
                      "
                      id="create_event_add_participant_button"
                    >
                      {{ selectedTeams.includes(team.id) ? 'anuluj' : 'dodaj' }}
                    </v-btn>
                  </v-col>
                </v-row>
              </v-card>
            </div>
          </div>
        </div>

        <!-- CARDS TEAMS ENDS -->

        <!-- CARD USERS STARTS -->

        <p id="event_details_caption" class="global_caption">
          Dodaj uczestników:
        </p>

        <div class="mx-auto ma-8 ml-8">
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

        <div
          id="create_event_participants_list"
          v-for="user in users"
          :key="user.id"
        >
          <div>
            <v-card
              id="create_event_participant"
              padding="20px"
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
                  <!-- chowa wiek, gdy za mało miejsca !-->
                  <v-card-title>wiek</v-card-title>
                  <v-card-text
                    v-text="calculateAge(user.dob) + ' lat'"
                  ></v-card-text>
                </v-col>

                <v-col class="text-no-wrap">
                  <v-btn
                    :color="selectedUsers.includes(user.id) ? 'error' : 'green'"
                    @click="addUserToEvent(user.id)"
                    id="create_event_add_participant_button"
                  >
                    {{ selectedUsers.includes(user.id) ? 'anuluj' : 'dodaj' }}
                  </v-btn>
                </v-col>
              </v-row>
            </v-card>
          </div>
        </div>

        <!-- CARDS USERS ENDING -->

        <div class="global_div_centerize">
          <label
            id="create_event_error"
            class="global_error"
            v-if="creatingEventFailed == 'input error'"
          >
            NIE UDAŁO SIĘ UTWORZYĆ WYDARZENIA. WYPEŁNIJ POPRAWNIE WSZYSTKIE POLA
            FORMULARZA.
          </label>
          <label
            id="create_event_error"
            class="global_error"
            v-if="creatingEventFailed == 'creating event failed'"
          >
            NIE UDAŁO SIĘ UTWORZYĆ WYDARZENIA. {{ message }}
          </label>
        </div>

        <div id="create_event_buttons_div" class="global_div_centerize">
          <v-btn
            id="create_event_button_edit"
            class="global_v_btn"
            type="submit"
            :loading="loading"
            >UTWÓRZ</v-btn
          >
          <br />
          <v-btn
            id="create_event_button_cancel"
            class="global_v_btn"
            to="/yourEvents"
            >ANULUJ</v-btn
          >
        </div>
      </form>
    </div>
  </v-app>
</template>


<script>
import UserService from '../services/user.service';
import EventService from '../services/event.service';
import TeamService from '../services/team.service';
import Event from '../models/event';
import json from '../resources/miasta.json';
import CalculateAge from '../utils/calculateAge';
import Vue from 'vue';
import Vuelidate from 'vuelidate';
import {
  required,
  maxLength,
  minValue,
  maxValue,
} from 'vuelidate/lib/validators';

export default {
  name: 'CreateEvent',
  data() {
    return {
      myJson: json,
      message: '',
      event: new Event('', '', '', '', '', '', '', [], ''),
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      users: '',
      selectedUsers: [],
      successful: false,
      creatingEventFailed: '',
      names: [''],
      selectedName: '',
      userTeamsIds: [],
      userTeamsWithPlayers: [],
      selectedTeams: [],
      loading: false,
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  validations: {
    event: {
      city: {
        required,
      },
      address: {
        required,
        maxLength: maxLength(50),
      },
      date: {
        required,
      },
      time: {
        required,
      },
      surface: {
        required,
      },
      limitation: {
        required,
        minValue: minValue(4),
        maxValue: maxValue(32),
      },
      description: {
        maxLength: maxLength(250),
      },
    },
  },
  methods: {
    handleCreateEvent() {
      this.loading = true;
      var that = this;

      if (this.event.participants.length > this.event.limitation) {
        this.message = 'ZWIĘKSZ LIMIT UCZESTNIKÓW LUB USUŃ KILKU GRACZY.';
        this.creatingEventFailed = 'creating event failed';
        this.loading = false;
        return;
      }
      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        this.creatingEventFailed = 'input error';
        this.loading = false;
      } else {
        EventService.createEvent(this.event).then(
          (data) => {
            this.message = data;
            if (
              this.message == 'Twoje wydarzenie zostało poprawnie opublikowane!'
            ) {
              this.successful = true;
              setTimeout(function () {
                that.$router.push('/yourEvents');
                that.loading = false;
              }, 500);
            }
          },
          (error) => {
            this.loading = false;
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            this.creatingEventFailed = 'creating event failed';
          }
        );
      }
    },
    calculateAge(userBirthday) {
      return CalculateAge.calculateAge(userBirthday);
    },
    addUserToEvent(userId) {
      if (this.selectedUsers.includes(userId)) {
        let index = this.selectedUsers.indexOf(userId);
        if (index >= 0) {
          this.selectedUsers.splice(index, 1);
        }
      } else {
        this.selectedUsers.push(userId);
      }

      if (this.event.participants.includes(userId)) {
        let inx = this.event.participants.indexOf(userId);
        if (inx >= 0) {
          this.event.participants.splice(inx, 1);
        }
      } else {
        this.event.participants.push(userId);
      }
    },
    addTeamToEvent(teamId, players) {
      this.selectedTeams.push(teamId);

      for (let i = 0; i < players.length; i++) {
        if (!this.event.participants.includes(players[i].id))
          this.event.participants.push(players[i].id);
        if (!this.selectedUsers.includes(players[i].id))
          this.selectedUsers.push(players[i].id);
      }
    },
    deleteTeamFromEvent(teamId, players) {
      let index2 = this.selectedTeams.indexOf(teamId);
      if (index2 >= 0) {
        this.selectedTeams.splice(index2, 1);
      }

      for (let i = 0; i < players.length; i++) {
        if (
          this.event.participants.includes(players[i].id) &&
          this.currentUser.id != players[i].id
        ) {
          let ind = this.event.participants.indexOf(players[i].id);
          if (ind >= 0) {
            this.event.participants.splice(ind, 1);
          }
        }

        if (this.selectedUsers.includes(players[i].id)) {
          let ind = this.selectedUsers.indexOf(players[i].id);
          if (ind >= 0) {
            this.selectedUsers.splice(ind, 1);
          }
        }
      }
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else {
      UserService.allUsers().then((data) => {
        for (let i = 0; i < data.length; i++) {
          this.names.push(data[i].name);
        }
        this.users = data;
      });
      this.event.organizer_id = this.currentUser.id;
      this.event.date = new Date().toISOString().substr(0, 10);

      this.selectedUsers.push(this.currentUser.id);
      this.event.participants.push(this.currentUser.id);

      TeamService.getUserTeams(this.currentUser.id).then((data) => {
        for (let i = 0; i < data.length; i++) {
          this.userTeamsIds.push(data[i].id);
        }
      });

      var that = this;
      setTimeout(function () {
        for (let i = 0; i < that.userTeamsIds.length; i++) {
          if (that.userTeamsIds[i]) {
            TeamService.getTeamTest(that.userTeamsIds[i]).then((data) => {
              that.userTeamsWithPlayers.push(data);
            });
          }
        }
      }, 500);
    }
  },
};
</script>

<style>
@import '../styles/style_create_event.css';
@import '../styles/style_global.css';
</style>
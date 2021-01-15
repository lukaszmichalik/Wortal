<template>
  <v-app id="event_create_app">
    <div id="event_create_div" class="event_create_div">
      <form name="editForm" @submit.prevent="handleAddEvent">
        <v-row flex>
          <v-col col="12" md="6">
            <p class="event_create_caption">Stwórz wydarzenie</p>

            <!-- <v-row class="event_create_row" justify="space-between"> -->
            <p class="event_create_label">Miasto:</p>

            <v-autocomplete
              v-model="event.city"
              :items="myJson"
              class="event_create_input"
              dense
              filled
              label="miejscowość"
            ></v-autocomplete>

            <p class="event_create_label">Adres:</p>

            <v-text-field
              class="event_create_input"
              type="text"
              v-model="event.address"
              v-validate="'min:6|max:30'"
              name="address"
              append-icon="mdi-map-marker"
              hint="np. Ul. Krakowska 10"
              persistent-hint
            />

            <p class="event_create_label">Data wydarzenia:</p>

            <v-date-picker
              :first-day-of-week="1"
              v-model="event.date"
              locale="pl-PL"
            ></v-date-picker>

            <!-- <v-row class="event_create_row" justify="space-between"> -->
            <p class="event_create_label">Godzina:</p>
            <input
              class="event_create_input"
              v-model="event.time"
              type="time"
              format="24hr"
              scrollable
            />


            <!-- <v-row class="event_create_row" justify="space-between"> -->
            <p class="event_create_label">Rodzaj nawierzchni:</p>
            <select
              required
              v-model="event.surface"
              class="event_create_input"
              name="surface"
            >
              <option disabled value="" selected hidden>
                rodzaj nawierzchni:
              </option>
              <option value="hala">hala</option>
              <option value="sztuczna">sztuczna/orlik</option>
              <option value="naturalna">naturalna trawa</option>
              <option value="tartan">tartan</option>
            </select>

            <p class="event_create_label">Limit uczestników:</p>

            <v-text-field
              v-model="event.limitation"
              class="event_create_input"
              hide-details
              single-line
              type="number"
              max="32"
              min="4"
              placeholder="12"
            ></v-text-field>

            <!-- <input
                class="event_create_input"
                type="text"
                v-model="event.limitation"
                v-validate="'required|min:3|max:20'"
                name="limitation"
              /> -->
            <!-- </v-row> -->

            <!-- <v-row class="event_create_row" justify="space-between"> -->

            <!-- </v-row> -->

            <!-- <v-row class="event_create_row" justify="space-between"> -->
            <p class="event_create_label">Dodaj opis:</p>

            <v-textarea
              max-width="80%"
              background-color="white"
              type="text"
              v-validate="'min:6|max:30'"
              v-model="event.description"
              name="description"
            />
            <!-- </v-row> -->

            <!-- CARD USERS STARTS -->

            <p id="event_details_caption" class="global_caption">
              Dodaj uczestników:
            </p>
            <v-card
              min-width="80%"
              padding="20px"
              v-for="user in users"
              :key="user.id"
              elevation="12"
            >
              <v-row>
                <v-col
                  class="hidden-sm-and-down"
                  align="center"
                  justify="center"
                >
                  <v-avatar color="indigo ma-5" size="50">
                    <span class="white--text headline">{{
                      getInitials(user.name)
                    }}</span>
                  </v-avatar>
                </v-col>

                <v-col class="text-no-wrap">
                  <v-card-title>Imię i Nazwisko</v-card-title>

                  <v-card-text v-text="user.name"></v-card-text>
                </v-col>

                <v-col class="text-no-wrap">
                  <v-card-title>Pozycja</v-card-title>

                  <v-card-text v-text="user.position"></v-card-text>
                </v-col>

                <v-col class="hidden-sm-and-down">
                  <v-card-title>Wiek</v-card-title>

                  <v-card-text v-text="calculateAge(user.dob)"></v-card-text>
                </v-col>

                <v-col class="text-no-wrap">
                  <v-btn
                    v-if="!selectedUsers.includes(user.id)"
                    @click="addTeammate(user, user.id)"
                    color="primary ma-5"
                    large
                    dark
                  >
                    Dodaj
                  </v-btn>
                </v-col>
              </v-row>
            </v-card>

            <!-- CARDS USERS ENDING -->

            <v-row class="event_create_row" justify="center">
              <div v-if="message" class="alert-danger">
                {{ message }}
              </div>
            </v-row>

            <v-row id="event_create_row_button" justify="space-around">
              <v-btn color="primary" class="mt-5" large dark> Anuluj </v-btn>

              <v-btn color="primary" class="mt-5" large dark type="submit">
                Zatwierdź
              </v-btn>
            </v-row>
          </v-col>
        </v-row>
      </form>
    </div>
  </v-app>
</template>

<script>
import UserService from '../services/user.service';
import EventService from '../services/event.service';
import Event from '../models/event';
import json from '../resources/miasta.json';
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
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    handleAddEvent() {
      console.log(this.event)
      EventService.createEvent(this.event)
    },

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
    addTeammate(user, index) {
      this.selectedUsers.push(index);
      this.event.participants.push(user);
      console.log(this.event.participants);
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    UserService.allUsers().then((data) => {
      this.users = data;
      console.log(this.users);
    });

    this.event.organizer_id = this.currentUser.id;
  },
};
</script>

<style>
@import '../styles/style_event_create.css';
@import '../styles/style_global.css';
select:invalid {
  color: gray;
}
option {
  color: black;
}
</style>
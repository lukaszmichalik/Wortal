<template>
  <v-app id="event_create_app">
    <div id="event_create_div" class="event_create_div">
      <form name="editForm" @submit.prevent="handleAddEvent">
        <v-row flex>
          <v-col>
            <p class="event_create_caption">Stwórz wydarzenie</p>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Miasto:</p>

              <v-autocomplete
                v-model="event.city"
                :items="myJson"
                dense
                filled
                label="Filled"
              ></v-autocomplete>
              <!-- <input
                required
                class="event_create_input"
                v-validate="'required|min:3|max:40'"
                type="text"
                name="miasto"
                v-model="event.city"
              /> -->
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Rodzaj nawierzchni:</p>
              <select
                required
                v-model="event.surface"
                class="event_create_input"
                name="surface"
              >
                <option value="hala">hala</option>
                <option value="sztuczna">sztuczna/orlik</option>
                <option value="naturalna">naturalna trawa</option>
                <option value="tartan">tartan</option>
              </select>
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Data wydarzenia:</p>

              <input
                class="event_create_input"
                type="datetime"
                v-model="event.date"
                name="date"
              />
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Czas trwania:</p>

              <input
                class="event_create_input"
                type="text"
                v-model="event.duration"
                v-validate="'required'"
                name="duration"
              />
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Limit uczestników:</p>

              <input
                class="event_create_input"
                type="text"
                v-model="event.limitation"
                v-validate="'required|min:3|max:20'"
                name="limitation"
              />
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Adres:</p>

              <input
                class="event_create_input"
                type="text"
                placeholder="Adres"
                v-model="event.address"
                v-validate="'min:6|max:30'"
                name="address"
              />
            </v-row>

            <v-row class="event_create_row" justify="space-between">
              <p class="event_create_label">Dodaj opis:</p>

              <v-textarea
                class="event_create_input"
                type="text"
                v-validate="'min:6|max:30'"
                v-model="event.description"
                name="description"
              />
            </v-row>

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
import AuthService from '../services/auth.service';
import Event from '../models/event';
import json from '../resources/miasta.json';
export default {
  name: 'CreateEvent',
  data() {
    return {
      myJson: json,
      message: '',
      event: new Event('', '', '', '', '', '', '', '', ''),
      userValue: JSON.parse(localStorage.getItem('user')) || '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    handleAddEvent() {
      console.log(this.event);
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
};
</script>

<style>
@import '../styles/style_event_create.css';
</style>
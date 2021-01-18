<template>
  <v-app id="create_event_app">
    <div id="create_event_div" class="global_div">
      <p id="ecreate_event_caption" class="global_caption">Utwórz wydarzenie</p>

      <form @submit.prevent="handleCreateEvent">
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

        <v-col id="create_event_col">
          <p id="create_event_label">data:</p>

          <p class="create_event_input_required" v-if="!$v.event.date.required">
            to pole jest wymagane
          </p>

          <input
            id="create_event_date"
            class="global_data_input"
            type="date"
            v-model="event.date"
            locale="pl-PL"
          />
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

        <!-- CARD USERS STARTS -->

        <p id="event_details_caption" class="global_caption">
          Dodaj uczestników:
        </p>

        <v-col id="create_event_participants_list">
          <div id="create_event_list">
            <v-card
              id="create_event_participant"
              padding="20px"
              v-for="user in users"
              :key="user.id"
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

                <v-col class="hidden-sm-and-down">          <!-- chowa wiek, gdy za mało miejsca !-->
                  <v-card-title>wiek</v-card-title>
                  <v-card-text v-text="calculateAge(user.dob)"></v-card-text>
                </v-col>

                <v-col class="text-no-wrap">
                  <v-btn
                    v-if="!selectedUsers.includes(user.id)"
                    @click="addTeammate(user.id)"
                    id="create_event_add_participant_button"
                  >
                    DODAJ
                  </v-btn>
                </v-col>
              </v-row>
            </v-card>
          </div>
        </v-col>

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
            NIE UDAŁO SIĘ UTWORZYĆ WYDARZENIA.
          </label>
        </div>

        <div id="create_event_buttons_div" class="global_div_centerize">
          <v-btn
            id="create_event_button_edit"
            class="global_v_btn"
            type="submit"
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
import Event from '../models/event';
import json from '../resources/miasta.json';
import Vue from 'vue';
import vuescroll from 'vue-scroll';
import Vuelidate from 'vuelidate';
import {
  required,
  maxLength,
  minValue,
  maxValue,
} from 'vuelidate/lib/validators';

Vue.use(vuescroll);

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
      //console.log(this.event);
      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        this.creatingEventFailed = 'input error';
      } else {
        EventService.createEvent(this.event).then(
          (data) => {
            //console.log('this is data below');
            //console.log(data);
            this.message = data;
            if (
              this.message ==
              'Twoje wydarzenie zostało poprawinie opublikowane!'
            ) {
              this.successful = true;
              this.$router.push('/yourEvents');
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
      let today = new Date();
      let birthDate = new Date(userBirthday);
      let difference = today - birthDate;
      let userAge = Math.floor(difference / 31557600000);
      return userAge;
    },
    addTeammate(userId) {
      this.selectedUsers.push(userId);
      this.event.participants.push(userId);
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

    this.selectedUsers.push(this.currentUser.id);
    this.event.participants.push(this.currentUser.id);
  },
};
</script>





<style>
@import '../styles/style_create_event.css';
@import '../styles/style_global.css';
</style>
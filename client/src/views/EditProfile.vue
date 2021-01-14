<template>
  <v-app id="edit_profile_app">
    <div id="edit_profile_div" class="global_div">
      <p id="edit_profile_caption" class="global_caption">Edytuj profil</p>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">imię i nazwisko:</p>

        <p class="edit_profile_input_required" v-if="!$v.user.name.required">
          to pole jest wymagane
        </p>
        <p class="edit_profile_input_required" v-if="!$v.user.name.maxLength">
          to pole może zawierać maksymalnie 50 znaków
        </p>

        <input
          id="edit_profile_name"
          class="global_data_input"
          type="text"
          v-model="user.name"
        />
      </v-col>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">pozycja:</p>
        <select
          v-model="user.position"
          class="global_data_input"
          name="position"
        >
          <option value="napastnik">napastnik</option>
          <option value="obrońca">obrońca</option>
          <option value="bramkarz">bramkarz</option>
          <option value="pomocnik">pomocnik</option>
        </select>
      </v-col>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">data urodzenia:</p>

        <p class="edit_profile_input_required" v-if="!$v.user.dob.required">
          to pole jest wymagane
        </p>

        <input
          id="edit_profile_dob"
          class="global_data_input"
          type="date"
          v-model="user.dob"
        />
      </v-col>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">adres e-mail:</p>

        <p class="edit_profile_input_required" v-if="!$v.user.email.required">
          to pole jest wymagane
        </p>
        <p class="edit_profile_input_required" v-if="!$v.user.email.email">
          adres e-mail jest niepoprawny
        </p>
        <p class="edit_profile_input_required" v-if="!$v.user.email.maxLength">
          to pole może zawierać maksymalnie 50 znaków
        </p>

        <input
          id="edit_profile_email"
          class="global_data_input"
          type="email"
          v-model="user.email"
        />
      </v-col>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">login:</p>

        <p
          class="edit_profile_input_required"
          v-if="!$v.user.username.required"
        >
          to pole jest wymagane
        </p>
        <p
          class="edit_profile_input_required"
          v-if="!$v.user.username.maxLength"
        >
          to pole może zawierać maksymalnie 50 znaków
        </p>

        <input
          id="edit_profile_login"
          class="global_data_input"
          type="text"
          v-model="user.username"
        />
      </v-col>

      <v-col id="edit_profile_col">
        <p id="edit_profile_label">hasło:</p>

        <p
          class="edit_profile_input_required"
          v-if="!$v.user.password.minLength"
        >
          hasło musi zawierać minimum 6 znaków
        </p>
        <p
          class="edit_profile_input_required"
          v-if="!$v.user.password.maxLength"
        >
          to pole może zawierać maksymalnie 120 znaków
        </p>

        <input
          id="edit_profile_password"
          class="global_data_input"
          type="password"
          placeholder="podaj nowe hasło"
          v-model="user.password"
        />
        <br />
        <input
          id="edit_profile_password"
          class="global_data_input"
          type="password"
          placeholder="powtórz nowe hasło"
          v-model="confirmPassword"
        />
      </v-col>

<div class="global_div_centerize">
      <label
        id="edit_profile_error"
        class="global_error"
        v-if="editFailed == 'input error'"
      >
        EDYCJA DANYCH NIE POWIODŁA SIĘ. WYPEŁNIJ POPRAWNIE WSZYSTKIE POLA
        FORMULARZA.
      </label>
      <label
        id="edit_profile_error"
        class="global_error"
        v-if="editFailed == 'different passwords'"
      >
        EDYCJA DANYCH NIE POWIODŁA SIĘ. PODANE HASŁA SIĘ RÓŻNIĄ.
      </label>
      <label
        id="edit_profile_error"
        class="global_error"
        v-if="editFailed == 'edit failed'"
      >
        EDYCJA DANYCH NIE POWIODŁA SIĘ.
      </label>
</div>

      <div id="edit_profile_buttons_div" class="global_div_centerize">
        <v-btn
          id="edit_profile_button_edit"
          class="global_v_btn"
          @click="handleEdit"
          >EDYTUJ</v-btn
        >
        <br />
        <v-btn
          id="edit_profile_button_cancel"
          class="global_v_btn"
          to="/userProfile"
          >ANULUJ</v-btn
        >
      </div>
    </div>
  </v-app>
</template>





<script>
import UserService from '../services/user.service';
import AuthService from '../services/auth.service';
import User from '../models/user';
import Vue from 'vue';
import Vuelidate from 'vuelidate';
import {
  required,
  email,
  minLength,
  maxLength,
} from 'vuelidate/lib/validators';

Vue.use(Vuelidate);

export default {
  name: 'EditProfile',
  data() {
    return {
      user: new User('', '', '', '', '', '', ''),
      confirmPassword: '',
      submitted: false,
      successful: false,
      message: '',
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      editFailed: '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  validations: {
    user: {
      name: {
        required,
        maxLength: maxLength(50),
      },
      dob: {
        required,
      },
      email: {
        required,
        email,
        maxLength: maxLength(50),
      },
      username: {
        required,
        maxLength: maxLength(50),
      },
      password: {
        minLength: minLength(6),
        maxLength: maxLength(120),
      },
    },
  },
  methods: {
    cancel() {
      this.$router.push('/userProfile');
    },
    handleEdit() {
      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        this.editFailed = 'input error';
      } else {
        if (this.user.password == this.confirmPassword) {
          //console.log('it went to api');
          this.user.id = this.currentUser.id;
          UserService.editUser(this.user).then(
            () => {
              //this.$router.push('/YourEvents');
              this.$router.push('/userProfile');
            },
            (error) => {
              this.loading = false;
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
                this.editFailed = "edit failed";
            }
          );
          
          
        } else {
          this.editFailed = 'different passwords';
        }
      }
    },
  },
  mounted() {
    this.user.name = this.currentUser.name;
    this.user.position = this.currentUser.position;
    this.user.dob = this.currentUser.dob;
    this.user.email = this.currentUser.email;
    this.user.username = this.currentUser.username;

    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
};
</script>





<style>
@import '../styles/style_edit_profile.css';
@import '../styles/style_global.css';
</style>
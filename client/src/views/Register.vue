<template>
  <v-app id="register_app">
    <div id="register_div" class="global_div">
      <div id="register_caption" class="global_caption">Utwórz konto</div>

      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="input">
            <div
              id="register_name_input_required"
              v-if="!$v.user.name.required"
              class="global_input_required"
            >
              to pole jest wymagane
            </div>
            <div
              id="register_name_input_required"
              v-if="!$v.user.name.maxLength"
              class="global_input_required"
            >
              to pole może zawierać maksymalnie 50 znaków
            </div>
            <input
              id="register_name"
              class="global_data_input"
              placeholder="imię i nazwisko"
              v-model="user.name"
              type="text"
              name="name"
            />
          </div>

          <div class="input">
            <select
              id="register_position"
              class="global_data_input"
              v-model="user.position"
            >
              <option value="napastnik">napastnik</option>
              <option value="obrońca">obrońca</option>
              <option value="bramkarz">bramkarz</option>
              <option value="pomocnik">pomocnik</option>
            </select>
          </div>

          <div class="input">
            <div v-if="!$v.user.dob.required" class="global_input_required">
              to pole jest wymagane
            </div>
            <input
              id="register_dob"
              class="global_data_input"
              type="date"
              v-model="user.dob"
              name="dob"
            />
          </div>

          <div class="input">
            <div v-if="!$v.user.email.required" class="global_input_required">
              to pole jest wymagane
            </div>
            <div v-if="!$v.user.email.email" class="global_input_required">
              adres e-mail jest niepoprawny
            </div>
            <div v-if="!$v.user.email.maxLength" class="global_input_required">
              to pole może zawierać maksymalnie 50 znaków
            </div>
            <input
              id="register_email"
              class="global_data_input"
              type="email"
              placeholder="adres e-mail"
              v-model="user.email"
              name="email"
            />
          </div>

          <div class="input">
            <div
              v-if="!$v.user.username.required"
              class="global_input_required"
            >
              to pole jest wymagane
            </div>
            <div
              v-if="!$v.user.username.maxLength"
              class="global_input_required"
            >
              to pole może zawierać maksymalnie 50 znaków
            </div>
            <input
              id="register_username"
              class="global_data_input"
              placeholder="login"
              v-model="user.username"
              type="text"
              name="username"
            />
          </div>

          <div class="input">
            <div
              v-if="!$v.user.password.required"
              class="global_input_required"
            >
              to pole jest wymagane
            </div>
            <div
              v-if="!$v.user.password.minLength"
              class="global_input_required"
            >
              hasło musi zawierać minimum 6 znaków
            </div>
            <div
              v-if="!$v.user.password.maxLength"
              class="global_input_required"
            >
              to pole może zawierć maksymalnie 120 znaków
            </div>
            <input
              id="register_password"
              class="global_data_input"
              type="password"
              placeholder="hasło"
              v-model="user.password"
              name="password"
            />
          </div>

          <label class="global_error" id="register_error" v-if="registerFailed == 'login in usage'">
            REJESTRACJA NIE POWIODŁA SIĘ. LOGIN JEST JUŻ ZAJĘTY.
          </label>
          <label class="global_error" id="register_error" v-if="registerFailed == 'email in usage'">
            REJESTRACJA NIE POWIODŁA SIĘ. ADRES E-MAIL JEST JUŻ W UŻYCIU.
          </label>
          <label class="global_error" id="register_error" v-if="registerFailed == 'input error'">
            REJESTRACJA NIE POWIODŁA SIĘ. WYPEŁNIJ POPRAWNIE WSZYSTKIE POLA FORMULARZA.
          </label>

          <div class="submit">
            <v-btn
              id="register_button_register"
              class="global_v_btn"
              type="submit"
            >
              UTWÓRZ KONTO
            </v-btn>
          </div>
        </div>
      </form>
    </div>
  </v-app>
</template>





<script>
import User from '../models/user';
import Vue from 'vue';
import Vuelidate from 'vuelidate';
import { required, email, minLength, maxLength } from 'vuelidate/lib/validators';

Vue.use(Vuelidate);

export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', 'napastnik', '', '', '', ''),
      submitted: false,
      successful: false,
      message: '',
      registerFailed: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/UserProfile');
    }
  },
  validations: {
    user: {
      name: {
        required,
        maxLength: maxLength(50)
      },
      dob: {
        required,
      },
      email: {
        required,
        email,
        maxLength: maxLength(50)
      },
      username: {
        required,
        maxLength: maxLength(50)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(120)
      },
    },
  },
  methods: {
    handleRegister() {
      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        this.registerFailed = 'input error';
      } else {
        this.$store.dispatch('auth/register', this.user).then(
          (data) => {
            this.message = data.message;
            //this.successful = true;
            this.$router.push('/Welcome');
          },
          (error) => {
            this.message =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();
            //this.successful = false;
            if (this.message == 'Błąd: Taki login już istnieje!') {
              this.registerFailed = 'login in usage';
            } else if (
              this.message == 'Błąd: Taki adres email jest już w użyciu!'
            ) {
              this.registerFailed = 'email in usage';
            }
          }
        );
      }
    },
  },
};
</script>






<style>
@import '../styles/style_register.css';
@import '../styles/style_global.css';
</style>
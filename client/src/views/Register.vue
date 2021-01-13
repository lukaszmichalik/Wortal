<template>
  <div id="register_div_background">
    <div id="register_space" />
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
            <input
              id="register_name"
              class="global_login_or_register_data_input"
              placeholder="imię i nazwisko"
              v-model="user.name"
              type="text"
              name="name"
            />
          </div>

          <div class="input">
            <select
              id="register_position"
              class="global_login_or_register_data_input"
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
              class="global_login_or_register_data_input"
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
            <input
              id="register_email"
              class="global_login_or_register_data_input"
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
            <input
              id="register_username"
              class="global_login_or_register_data_input"
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
            <input
              id="register_password"
              class="global_login_or_register_data_input"
              type="password"
              placeholder="hasło"
              v-model="user.password"
              name="password"
            />
          </div>

          <div class="submit">
            <v-btn
              id="register_button_register"
              class="global_v_btn"
              type="submit"
            >
              UTWÓRZ KONTO
            </v-btn>
          </div>

<div v-if="registerFailed">REJESTRACJA SIĘ NIE POWIODŁA</div>

        </div>
      </form>
    </div>
  </div>
</template>





<script>
import User from '../models/user';
import Vue from 'vue';
import Vuelidate from 'vuelidate';
import { required, email, minLength } from 'vuelidate/lib/validators';

Vue.use(Vuelidate);

export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', 'napastnik', '', '', '', ''),
      submitted: false,
      successful: false,
      message: '',
      var: registerFailed = 0
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
      },
      dob: {
        required,
      },
      email: {
        required,
        email,
      },
      username: {
        required,
      },
      password: {
        required,
        minLength: minLength(6),
      },
    },
  },
  methods: {
    handleRegister() {
      this.$v.$touch();
      if (this.$v.$pendind || this.$v.$error) {
        alert('Rejestracja nie powiodła się');
      } else {
        this.$store.dispatch('auth/register', this.user).then(
          (data) => {
            this.message = data.message;
            //this.successful = true;
            console.log(this.message);
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
            console.log(this.message);
            if (this.message == 'Błąd: Taki login już istnieje!') {
              registerFailed = 1;
              alert(
                'Rejestracja nie powiodła się. Podany login jest już w użyciu.'
              );
            } else if (this.message == 'Błąd: Taki adres email jest już w użyciu!') {
              alert(
                'Rejestracja nie powiodła się. Podany adres e-mail jest już w użyciu.'
              );
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
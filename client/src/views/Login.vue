<template>
  <v-app id="login_app">
    <div id="login_div" class="global_div">
      <img
        id="login_app_logo"
        alt="Vue logo"
        src="../assets/official_logo.png"
      />

      <form @submit.prevent="handleLogin">
        <input
          id="login_username"
          class="global_data_input"
          type="text"
          placeholder="login"
          v-model="user.username"
        />
        <br />
        <input
          id="login_password"
          class="global_data_input"
          type="password"
          placeholder="hasło"
          v-model="user.password"
        />
        <br />

        <label
          class="global_error"
          id="login_error"
          v-if="loginFailed == 'login failed'"
        >
          NIE UDAŁO SIĘ ZALOGOWAĆ.
        </label>
        <label
          class="global_error"
          id="login_error"
          v-if="loginFailed == 'empty fields'"
        >
          WYPEŁNIJ WSZYSTKIE POLA FORMULARZA.
        </label>
        <br />

        <v-btn id="login_button_login" class="global_v_btn" type="submit"
          >ZALOGUJ</v-btn
        >
        <br />
        <v-btn id="login_button_register" class="global_v_btn" to="/Register"
          >UTWÓRZ KONTO</v-btn
        >
      </form>
    </div>
  </v-app>
</template>





<script>
import User from '../models/user';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: '',
      loginFailed: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/UserProfile');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then((isValid) => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/YourEvents');
            },
            (error) => {
              this.loading = false;
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
              this.loginFailed = 'login failed';
            }
          );
        } else {
          this.loginFailed = 'empty fields';
        }
      });
    },
  },
};
</script>





<style>
@import '../styles/style_login.css';
@import '../styles/style_global.css';
</style>
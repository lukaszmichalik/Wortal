<template>
  <div id="register_div_background">
    <div id="register_space" />
    <div id="register_div" class="global_div">
      <div id="register_caption" class="global_caption">Utwórz konto</div>
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="input">
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
            <input
              id="register_dob"
              class="global_login_or_register_data_input"
              type="date"
              v-model="user.dob"
              name="dob"
            />
          </div>

          <div class="input">
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
        </div>
      </form>
    </div>
  </div>
</template>





<script>
import User from '../models/user';

export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', 'napastnik', '', '', '', ''),
      submitted: false,
      successful: false,
      message: '',
    };
  },
  computed: {
    loggedIn() {
      console.log('loggedIn');
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/UserProfile');
    }
  },
  methods: {
    handleRegister() {
      this.$store
        .dispatch('auth/register', this.user)
        .then(this.$router.push('/Welcome'));
    },
  },
};
</script>





<style>
@import '../styles/style_register.css';
@import '../styles/style_global.css';
</style>
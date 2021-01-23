<template>
    <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <p id="user_profile_caption" class="global_caption">Profil użytkownika</p>

      <v-row id="user_profile_row">
        <p id="user_profile_label">imię i nazwisko:</p>
        <p id="user_profile_name" class="user_profile_data">
          {{ currentUser.name }}
        </p>
      </v-row>

      <v-row id="user_profile_row">
        <p id="user_profile_label">pozycja:</p>
        <p id="user_profile_lastname" class="user_profile_data">
          {{ currentUser.position }}
        </p>
      </v-row>

      <v-row id="user_profile_row">
        <p id="user_profile_label">data urodzenia:</p>
        <p id="user_profile_age" class="user_profile_data">
          {{ formatDate(currentUser.dob) }}
        </p>
      </v-row>

      <v-row id="user_profile_row">
        <p id="user_profile_label">adres e-mail:</p>
        <p id="user_profile_login" class="user_profile_data">
          {{ currentUser.email }}
        </p>
      </v-row>

      <v-row id="user_profile_row">
        <p id="user_profile_label">login:</p>
        <p id="user_profile_login" class="user_profile_data">
          {{ currentUser.username }}
        </p>
      </v-row>

      <br />
      <div id="user_profile_buttons_div" class="global_div_centerize">
        <v-btn id="user_profile_button_edit" class="global_v_btn" to="/EditProfile">EDYTUJ</v-btn>
        <br />
        <v-btn
          id="user_profile_button_delete_account"
          class="global_v_btn"
          to="/DeleteAccount"
          >USUŃ KONTO</v-btn
        >
      </div>
    </div>
    </v-app>
</template>

<script>
import DateFormatter from '../utils/dateFormatter';

export default {
  name: 'UserProfile',
  data(){
    return {
      userValue: JSON.parse(localStorage.getItem('user')) || '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
    
  },
  methods:{
    formatDate(date) {
     return DateFormatter.formatDate(date)
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
@import '../styles/style_user_profile.css';
</style>
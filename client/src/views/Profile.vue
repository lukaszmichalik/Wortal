<template>
  <!-- <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    <p>
      <strong>Position:</strong>
      {{currentUser.position}}
    </p>
    <p>
      <strong>Date of birth:</strong>
      {{currentUser.dob}}
    </p>
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div> -->
  <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <p id="user_profile_caption" class="global_caption">Profil użytkownika</p>

        <v-row id="user_profile_row">
          <p id="user_profile_label">imię:</p>
          <p id="user_profile_name" class="user_profile_data">{{currentUser.username}}</p>
        </v-row>

        <v-row id="user_profile_row">
          <p id="user_profile_label">nazwisko:</p>
          <p id="user_profile_lastname" class="user_profile_data">Nowak</p>
        </v-row>

        <v-row id="user_profile_row">
          <p id="user_profile_label">wiek:</p>
          <p id="user_profile_age" class="user_profile_data">26</p>
        </v-row>

        <v-row id="user_profile_row">
          <p id="user_profile_label">login:</p>
          <p id="user_profile_login" class="user_profile_data">adamnowak</p>
        </v-row>

        <v-row id="user_profile_row">
          <p id="user_profile_label_password">hasło:</p>
          <p id="user_profile_password" class="user_profile_data">***********</p>
        </v-row>

    </div>
  </v-app>
</template>

<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>

<style>
@import "../styles/style_user_profile.css";
</style>
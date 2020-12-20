<template>
  <v-app id="user_profile_app">
    <sidebar/>
    <div id="user_profile_div" class="global_div">
      <p id="user_profile_caption" class="global_caption">Profil użytkownika</p>

        <v-row id="user_profile_row">
          <p id="user_profile_label">imię:</p>
          <p id="user_profile_name" class="user_profile_data">Adam</p>
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
import Sidebar from '../navigation/Sidebar'

export default {
  components: {
    Sidebar
  }
}
</script>





<style>
@import "../../styles/style_user_profile.css";
</style>
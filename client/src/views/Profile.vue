<template>
  <v-app id="user_profile_app" v-if="currentUser">
    <div id="user_profile_div" class="global_div">
      <v-col>
        <p id="user_profile_caption" class="global_caption">Profil użytkownika</p>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <p id="user_profile_name" class="user_profile_data">{{currentUser.name}}</p>
          </v-row>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Pozycja</p>
            <p id="user_profile_lastname" class="user_profile_data">{{currentUser.position}}</p>
          </v-row>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Data urodzenia:</p>
            <p id="user_profile_age" class="user_profile_data">{{currentUser.dob}}</p>
          </v-row>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Email:</p>
            <p id="user_profile_login" class="user_profile_data">{{currentUser.email}}</p>
          </v-row>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Username:</p>
            <p id="user_profile_login" class="user_profile_data" >{{currentUser.username}}</p>
          </v-row>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label_password">hasło:</p>
            <p id="user_profile_password" class="user_profile_data">**********</p>
          </v-row>
  
          <v-row id="user_profile_row_button">
            <v-btn   
              color="primary"
              large
              dark
              to="/editProfile"
            >
              Edytuj
            </v-btn>
          </v-row>
      </v-col>

  </div>
    
  </v-app>
</template>

<script>
export default {
  name: 'Profile',
  data(){
    return {
      userValue: JSON.parse(localStorage.getItem('user')) || '',
    };
  },
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
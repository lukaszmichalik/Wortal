<template>
  <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <v-col>
        <p id="user_profile_caption" class="global_caption">
          Profil użytkownika
        </p>

        <v-row id="user_profile_row">
          <p id="user_profile_label">Imię i Nazwisko:</p>
          <input
            class="global_login_or_register_data_input"
            v-validate="'required|min:3|max:20'"
            type="text"
            name="name"
            :value="currentUser.name"
          />
        </v-row>
      </v-col>
    </div>
  </v-app>
</template>





<script>
export default {
  name: 'UserProfile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/Login');
    }
  },
};
</script>





<style>
@import '../styles/style_user_profile.css';
@import '../styles/style_register.css';
</style>
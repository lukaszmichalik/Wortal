<template>
  <div id="edit_profile_background">
    <div id="edit_profile_space" />
    <div id="edit_profile_div" class="global_div">
      <p id="edit_profile_caption" class="global_caption">Edytuj profil</p>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">imię i nazwisko:</p>
        <input
          id="edit_profile_name"
          class="global_login_or_register_data_input"
          type="text"
          name="name"
          :value="currentUser.name"
        />
      </v-row>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">pozycja:</p>
        <input
          id="edit_profile_position"
          class="global_login_or_register_data_input"
          type="text"
          name="position"
          :value="currentUser.position"
        />
      </v-row>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">data urodzenia:</p>
        <input
          id="edit_profile_dob"
          class="global_login_or_register_data_input"
          type="date"
          name="dob"
          :value="currentUser.dob"
        />
      </v-row>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">adres e-mail:</p>
        <input
          id="edit_profile_email"
          class="global_login_or_register_data_input"
          type="email"
          name="email"
          :value="currentUser.email"
        />
      </v-row>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">login:</p>
        <input
          id="edit_profile_login"
          class="global_login_or_register_data_input"
          type="text"
          name="login"
          :value="currentUser.username"
        />
      </v-row>

      <v-row id="edit_profile_row">
        <p id="edit_profile_label">hasło:</p>
        <input
          id="edit_profile_password"
          class="global_login_or_register_data_input"
          type="password"
          placeholder="podaj nowe hasło"
          name="password"
        />
      </v-row>

      <br />
      <div id="edit_profile_buttons_div" class="global_div_centerize">
        <v-btn id="edit_profile_button_ok" class="global_v_btn">OK</v-btn>
        <br />
        <v-btn
          id="edit_profile_button_cancel"
          class="global_v_btn"
          to="/UserProfile"
          >ANULUJ</v-btn
        >
      </div>
    </div>
  </div>
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
@import '../styles/style_edit_profile.css';
</style>



















<!--<template>
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
!-->
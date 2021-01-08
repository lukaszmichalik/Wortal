<template>
  <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <v-col >
        <p id="user_profile_caption" class="global_caption">
          Profil użytkownika
        </p>

        <v-row id="user_profile_row" justify="space-between">
          <p id="user_profile_label">Imię i Nazwisko:</p>
          <input
            id="register_name"
            class="global_login_or_register_data_input"
            v-validate="'required|min:3|max:20'"
            type="text"
            name="name"
            v-model="currentUser.name"
          />
        </v-row>

        <v-row id="user_profile_row" justify="space-between">
          <p id="user_profile_label">Pozycja:</p>
          <select
            v-model="currentUser.position"
            id="register_position"
            class="global_login_or_register_data_input"
            name="position"
          >
            <option value="Napastnik">Napastnik</option>
            <option value="Obrońca">Obrońca</option>
            <option value="Bramkarz">Bramkarz</option>
            <option value="Pomocnik">Pomocnik</option>
          </select>
        </v-row>
        
        <v-row id="user_profile_row" justify="space-between">
          <p id="user_profile_label">Data Urodzenia:</p>

            <input
              id="register_age"
              class="global_login_or_register_data_input"
              type="date"
              v-model="currentUser.dob"
              name="dob"
            />

        </v-row>

        <v-row id="user_profile_row" justify="space-between">
          <p id="user_profile_label">Email:</p>

            <input
              id="register_email"
              class="global_login_or_register_data_input"
              type="email"
              v-model="currentUser.email"
              name="email"
              
            />

        </v-row>

        <v-row id="user_profile_row" justify="space-between">
          <p id="user_profile_label">Username:</p>

            <input
              id="register_username"
              class="global_login_or_register_data_input"
              type="text"
              v-model="currentUser.username"
              name="username"
            />

        </v-row>

        <v-row id="user_profile_row_button" justify="space-around" >
            <div>
                <v-btn   
                  color="primary"
                  large
                  dark
                  to="/profile"
                >
                  Anuluj
                </v-btn>
            </div>
            <div>
                <v-btn   
                  color="primary"
                  large
                  dark
                  to="/home"
                >
                  Zatwierdź
                </v-btn>
            </div>
          </v-row>

        <!-- <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input 
                class="global_login_or_register_data_input"
                v-validate="'required|min:3|max:20'"
                type="text"
                name="name"
                :value="currentUser.name"/>
          </v-row>
          <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input 
                class="global_login_or_register_data_input"
                v-validate="'required|min:3|max:20'"
                type="text"
                name="name"
                :value="currentUser.name"/>
          </v-row>

          <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input 
                class="global_login_or_register_data_input"
                v-validate="'required|min:3|max:20'"
                type="text"
                name="name"
                :value="currentUser.name"/>
          </v-row>
          <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input 
                class="global_login_or_register_data_input"
                v-validate="'required|min:3|max:20'"
                type="text"
                name="name"
                :value="currentUser.name"/>
          </v-row> -->
      </v-col>
    </div>
  </v-app>
</template>

<script>

export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods:{
      UpdateSelected(){
          this.selected = this.currentUser.position;
          return this.selected
      }
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
@import '../styles/style_register.css';
</style>
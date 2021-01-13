<template>
  <v-app id="user_edit_app">
    <div id="user_edit_div" class="user_edit_div">
      <form name="editForm" @submit.prevent="handleEdit">
        <v-row flex>
          <v-col>
            <p class="user_edit_caption">
              Profil użytkownika
            </p>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Imię i Nazwisko:</p>
              <input
                required
                class="user_edit_input"
                v-validate="'required|min:3|max:40'"
                type="text"
                name="name"
                v-model="user.name"
              />
            </v-row>
  
            <v-row  class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Pozycja:</p>
              <select
                required
                v-model="user.position"
                
                class="user_edit_input"
                name="position"
              >
                <option value="napastnik">napastnik</option>
                <option value="obrońca">obrońca</option>
                <option value="bramkarz">bramkarz</option>
                <option value="pomocnik">pomocnik</option>
              </select>
            </v-row>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Data Urodzenia:</p>
  
              <input
                required
                class="user_edit_input"
                type="date"
                v-model="user.dob"
                v-validate="'required'"
                name="dob"
              />
            </v-row>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Email:</p>
  
              <input
                class="user_edit_input"
                type="email"
                v-model="user.email"
                v-validate="'required|email|max:30'"
                name="email"
              />
            </v-row>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Username:</p>
  
              <input
                class="user_edit_input"
                type="text"
                v-model="user.username"
                v-validate="'required|min:3|max:20'"
                name="username"
              />
            </v-row>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Nowe Hasło:</p>
  
              <input
                class="user_edit_input"
                type="password"
                placeholder="Wprowadź nowe hasło"
                v-model="user.password"
                v-validate="'min:6|max:30'"
                name="password"
              />
            </v-row>
  
            <v-row class="user_edit_row" justify="space-between">
              <p class="user_edit_label">Potwierdź Hasło:</p>
  
              <input
                class="user_edit_input"
                type="password"
                placeholder="Potwierdź nowe hasło"
                v-validate="'min:6|max:30'"
                v-model="confirmPassword"
                name="confirmPassword"
              />
            </v-row>
  
            <v-row class="user_edit_row" justify="center">
              <div v-if="message" class="alert-danger">
                {{ message }}
              </div>
            </v-row>
  
            <v-row id="user_edit_row_button" justify="space-around">
              
                <v-btn color="primary" class="mt-5" large dark @click="cancel"> Anuluj </v-btn>
              
                <v-btn color="primary" class="mt-5" large dark type="submit">
                  Zatwierdź
                </v-btn>
              
            </v-row>
          </v-col>
        </v-row>
      </form>
    </div>
  </v-app>
</template>

<script>
import UserService from '../services/user.service';
import AuthService from '../services/auth.service';
import User from '../models/user';
export default {
  name: 'EditProfile',
  data() {
    return {
      user: new User('', '', '', '', '', '', ''),
      confirmPassword: '',
      submitted: false,
      successful: false,
      message: '',
      userValue: JSON.parse(localStorage.getItem('user')) || '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    cancel() {
      this.$router.push('/userProfile');
    },
    handleEdit() {
      if (this.user.password == this.confirmPassword) {
        console.log('it went to api');
        this.user.id = this.currentUser.id;
        UserService.editUser(this.user);

        if (this.user.username != this.currentUser.username) {
          this.$store.dispatch('auth/logout');
          this.$router.push('/login');
        }
      } else {
        this.message = 'Nie pasujące hasła';
      }
    },
  },
  mounted() {
    this.user.name = this.currentUser.name;
    this.user.position = this.currentUser.position;
    this.user.dob = this.currentUser.dob;
    this.user.email = this.currentUser.email;
    this.user.username = this.currentUser.username;

    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
};
</script>

<style>
@import '../styles/style_user_edit.css';
</style>
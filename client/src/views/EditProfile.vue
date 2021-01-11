<template>
  <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <v-col>
        <p id="user_profile_caption" class="global_caption">Profil użytkownika</p>
  
          <v-row id="user_profile_row">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input 
                class="global_login_or_register_data_input"
                v-validate="'required|min:3|max:20'"
                type="text"
                name="name"
                :value="currentUser.name"/>
          </v-row>
  
          <!-- <v-row id="user_profile_row">
            <p id="user_profile_label">Pozycja:</p>
            <select  
              id="register_position" 
              class="global_login_or_register_data_input"
              :value="user.position">

                <option :value="user.position" selected>{{user.position}}</option>
              <option value="Napastnik">Napastnik</option>
              <option value="Obrońca">Obrońca</option>
              <option value="Bramkarz">Bramkarz</option>
              <option value="Pomocnik">Pomocnik</option>

            </select>
          </v-row> -->
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
  name: 'UserProfile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/Login');
    }
  }
};
</script>

<style>
@import "../styles/style_user_profile.css";
@import "../styles/style_register.css";
</style>



















<!--<template>
  <v-app id="user_profile_app">
    <div id="user_profile_div" class="global_div">
      <form name="editForm" @submit.prevent="handleEdit">
        <v-col >
          <p id="user_profile_caption" class="global_caption">
            Profil użytkownika
          </p>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Imię i Nazwisko:</p>
            <input required
              class="global_login_or_register_data_input"
              v-validate="'required|min:3|max:40'"
              type="text"
              name="name"
              v-model="user.name"
            />
          </v-row>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Pozycja:</p>
            <select required
              v-model="user.position"
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
  
              <input required
                
                class="global_login_or_register_data_input"
                type="date"
                v-model="user.dob"
                v-validate="'required'"
                name="dob"
              />
  
          </v-row>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Email:</p>
  
              <input
                
                class="global_login_or_register_data_input"
                type="email"
                v-model="user.email"
                v-validate="'required|email|max:30'"
                name="email"
                
              />
  
          </v-row>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Username:</p>
  
              <input
                
                class="global_login_or_register_data_input"
                type="text"
                v-model="user.username"
                v-validate="'required|min:3|max:20'"
                name="username"
              />
  
          </v-row>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Nowe Hasło:</p>
  
              <input
                
                class="global_login_or_register_data_input"
                type="password"
                placeholder="Wprowadź nowe hasło"
                v-model="user.password"
                v-validate="'min:6|max:30'"
                name="password"
              />
  
          </v-row>
  
          <v-row id="user_profile_row" justify="space-between">
            <p id="user_profile_label">Potwierdź Hasło:</p>
  
              <input
                
                class="global_login_or_register_data_input"
                type="password"
                placeholder="Potwierdź nowe hasło"
                v-validate="'min:6|max:30'"
                v-model="confirmPassword"
                name="confirmPassword"
              />
  
          </v-row>

          <v-row  id="user_profile_row" justify="center">
            <div
              v-if="message"
              class="alert-danger"
            >
              {{ message }}
  
            </div>
          </v-row>
  
          <v-row id="user_profile_row_button" justify="space-around" >
              <div>
                  <v-btn   
                    color="primary"
                    large
                    dark
                    @click="cancel"
                  >
                    Anuluj
                  </v-btn>
              </div>
              <div>
                  <v-btn   
                    color="primary"
                    large
                    dark
                    type="submit"
                    
                  >
                    Zatwierdź
                  </v-btn>
              </div>
            </v-row>
  
        </v-col>
      </form>

      

    </div>
  </v-app>
</template>

<script>
import UserService from '../services/user.service';
import AuthService from '../services/auth.service';
import User from '../models/user';

export default {
  name: 'Profile',
  data() {
    return {
      user: new User('','', '', '', '','',''),
      confirmPassword:'',
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
  methods:{
      cancel(){
        this.$router.push('/profile');
      },
      handleEdit() {
        
        console.log('message' + this.message)
        console.log('userpassword:' + this.user.password)
        console.log('confirm: ' +this.confirmPassword)
        console.log('user username:' + this.user.username)
        console.log('currentuser username: ' +this.currentUser.username)


        if(this.user.password==this.confirmPassword){
          console.log('it went to api')
          this.user.id = this.currentUser.id
          UserService.editUser(this.user)
          
          if(this.user.username!=this.currentUser.username){
            this.$store.dispatch('auth/logout');
            this.$router.push('/login');
          }
        }else{
          this.message="Nie pasujące hasła"
        }
        
      }
  },
  mounted() {
    //this.user = this.currentUser
    this.user.name = this.currentUser.name
    this.user.position = this.currentUser.position
    this.user.dob = this.currentUser.dob
    this.user.email = this.currentUser.email
    this.user.username = this.currentUser.username
    
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    // UserService.editUser(this.user);
  },
};
</script>

<style>
@import '../styles/style_user_profile.css';
@import '../styles/style_register.css';
</style>!-->
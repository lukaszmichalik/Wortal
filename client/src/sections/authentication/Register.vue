<template>
  <v-app id="register_app">
    <div id="register_div" class="global_div">
      <div
          id="register_caption"
          class="global_caption">
          Utwórz konto
        </div>

      <!-- <p id="register_caption" class="global_caption">Utwórz konto</p>
      <input id="register_name" class="global_login_or_register_data_input" placeholder="imię">
      <br>
      <input id="register_lastname" class="global_login_or_register_data_input" placeholder="nazwisko">
      <br>
      <input id="register_age" class="global_login_or_register_data_input" placeholder="wiek">
      <br>
      <input id="register_login" class="global_login_or_register_data_input" placeholder="login">
      <br>
      <input type="password" id="register_password" class="global_login_or_register_data_input" placeholder="hasło">
      <br>
      <v-btn id="register_button_register" class="global_v_btn" to="/">UTWÓRZ KONTO</v-btn> -->
    <form @submit.prevent="onSubmit">
        
        <div class="input">
          <input
            id="register_lastname"
            class="global_login_or_register_data_input"
            type="fullname"
            placeholder="Imię i Nazwisko"
            v-model="fullname">
        </div>
        <div class="input">
          <input
            id="register_age"
            class="global_login_or_register_data_input"
            type="date"
            placeholder="Wiek"
            v-model="dob">
        </div>
        <div class="input">
          <input
            id="register_login"
            class="global_login_or_register_data_input"
            type="email"
            placeholder="Email"
            v-model="email">
        </div>
        <div class="input">
          <input
            id="register_password"
            class="global_login_or_register_data_input"
            type="password"
            placeholder="Hasło"
            v-model="password">
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
      </form>
    </div>
  </v-app>
</template>


<script>
  import globalAxios from 'axios'

  export default {
    data () {
      return {
        fullname: '',
        dob: null,
        email: '',
        password: '',
      }
    },
    methods: {
      onSubmit () {
        const formData = {
          fullname: this.fullname,
          dob: this.dob,
          email: this.email,
          password: this.password,
        }
        console.log(formData)
        console.log('u clicked me')
        //this.$store.dispatch('signup', formData)
        globalAxios.post('http://localhost:8080/register', formData)
        .then(res => console.log(res))
        .catch(error => console.log(error))
      }
    }
  }
</script>


<style>
@import "../../styles/style_register.css";
</style>

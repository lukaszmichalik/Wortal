<template>
  <v-app id="register_app">
    <div id="register_div" class="global_div">

      <p id="register_caption" class="global_caption">Utwórz konto</p>
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
      <v-btn id="register_button_register" class="global_v_btn" to="/">UTWÓRZ KONTO</v-btn>
    
    </div>
  </v-app>
</template>





<style>
@import "../../styles/style_register.css";
</style>

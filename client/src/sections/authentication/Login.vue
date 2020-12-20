<template>
  <v-app id="login_app">
    <div id="login_div" class="global_div">

      <img id="login_app_logo" alt="Vue logo" src="../../assets/official_logo.png">
      <br>
      <input id="login_login" class="global_login_or_register_data_input" placeholder="login" >
      <br>
      <input type="password" id="login_password" class="global_login_or_register_data_input" placeholder="hasło">
      <br>
      <v-btn id="login_button_login" class="global_v_btn" to="/YourEvents">ZALOGUJ</v-btn>
      <br>
      <v-btn id="login_button_register" class="global_v_btn" to="/Register">UTWÓRZ KONTO</v-btn>
    
    </div>
  </v-app>
</template>





<style>
@import "../../styles/style_login.css";
@import "../../styles/style_global.css";
</style>
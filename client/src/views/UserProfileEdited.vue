<template>
  <v-app id="welcome_app">
    <div id="welcome_space" />
    <div id="welcome_div" class="global_div">
      <div id="welcome_caption">
        Twoje dane profilu zostały zmienione. Zaloguj się ponownie aby
        kontynuować.
      </div>

      <br />
      <v-btn id="welcome_button_login" class="global_v_btn" to="/Login"
        >ZALOGUJ SIĘ</v-btn
      >
    </div>
  </v-app>
</template>





<style>
@import '../styles/style_welcome.css';
@import '../styles/style_global.css';
</style>
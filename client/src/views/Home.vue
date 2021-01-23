<template>
  <!--<div id="home_div_background">!-->
    <v-app id="home_app">
    <div id="home_div" class="global_div">
      <img
        id="home_app_logo"
        alt="Vue logo"
        src="../assets/official_logo.png"
      />

      <div id="home_caption">Witaj!</div>
      <div id="home_description">
        Przedstawiamy Ci wyjątkowe miejsce w sieci. Miejsce, które da ci
        możliwość realizacji twoich piłkarskich zainteresowań. Miejsce, w którym
        poznasz osoby podzielające twoje pasje. Miejsce, które pozwoli ci na
        dołączanie do wydarzeń piłkarskich czy organizację własnych spotkań.
        Miejsce, dzięki któremu dołączysz do nowych drużyn piłkarskich i
        stworzysz swoje własne. Nie zwlekaj i już dziś dołącz do naszej
        społeczności, aby móc w pełni cieszyć się z oferowanych przez nas
        możliwości.
      </div>

      <br />
      <v-btn id="home_button_login" class="global_v_btn" to="/Login"
        >ZALOGUJ SIĘ</v-btn
      >
      <br />
      <v-btn id="home_button_register" class="global_v_btn" to="/Register"
        >ZAREJESTRUJ SIĘ</v-btn
      >
    </div>
  <!--</div>!-->
    </v-app>
</template>

<style>
@import '../styles/style_home.css';
@import '../styles/style_global.css';
</style>
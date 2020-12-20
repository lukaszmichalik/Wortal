<template>
  <v-app id="create_team_app" class="global_app">
    <sidebar/>
    <div id="create_team_div" class="global_div">
      <p id="create_team_caption" class="global_caption">Utwórz drużynę</p>


      <v-layout justify-center>
        <v-form class="global_card">
          <p id="create_team_p" class="global_p">nazwa:</p>
          <div class="global_div_centerize">
            <input id="create_team_name" class="global_full_width_input">
          </div>

          <p id="create_team_p" class="global_p">członkowie:</p>
          <div class="global_div_centerize">
            <textarea id="create_team_participants" class="global_full_width_input"></textarea>
          </div>

          <div class="global_div_centerize">
            <v-btn id="create_team_button_create" class="global_v_btn" to="/TeamDetails">UTWÓRZ</v-btn>
          </div>

        </v-form>
      </v-layout>

    </div>
  </v-app>
</template>




<script>
import Sidebar from '../navigation/Sidebar'

export default {
  components: {
    Sidebar
  }
}
</script>





<style>
@import "../../styles/style_create_team.css";
</style>
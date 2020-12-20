<template>
  <v-app id="team_details_app" class="global_app">
    <sidebar/>
    <div id="team_details_div" class="global_div">
      <p id="team_details_caption" class="global_caption">Szczegóły drużyny</p>
        
      <v-layout justify-center>
        <v-form  class="global_card">
          <p id="team_details_name" class="global_details_p">Najlepsza drużynaooooooooooooooooooooooooooooooooooooooo</p>

          <ul id="team_details_participants" class="global_participants">
            <li>Jan Kowalski</li>
            <li>Adam Nowak</li>
            <li>Piotr Nowakowski</li>
          </ul>

          <br>

          <div class="global_div_centerize">
            <v-btn id="team_details_button_join" class="global_v_btn">DOŁĄCZ</v-btn>
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
@import "../../styles/style_team_details.css";
@import "../../styles/style_global.css";
</style>
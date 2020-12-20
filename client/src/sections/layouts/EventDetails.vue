<template>
  <v-app id="event_details_app" class="global_app">
    <sidebar/>
    <div id="event_details_div" class="global_div">
      <p id="event_details_caption" class="global_caption">Szczegóły wydarzenia</p>
        
        <v-layout justify-center>
          <v-form class="global_card">
            <p id="event_details_type" class="global_details_p">trening</p>
            <p id="event_details_date" class="global_details_p">14 listopada 2020 (sobota)</p>
            <p id="event_details_time" class="global_details_p">11:00 - 13:00</p>
            <p id="event_details_address" class="global_details_p">Hala Sportowa II Liceum Ogólnokształcącego w Krakowieddddddddddddddddddddddddddddddddddddddddddddddd</p>

            <ul id="event_details_participants" class="global_participants">
              <li>Jan Kowalskioooooooooooooooooooooooooooooooooooo</li>
              <li>Adam Nowak</li>
              <li>Piotr Nowakowski</li>
            </ul>

            <p id="event_details_details" class="global_details_p">Po treningu planowane jest wspólne wyjście do KFC ;) mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm</p>

            <div class="global_div_centerize">
              <v-btn id="event_details_button_join" class="global_v_btn">WEŹ UDZIAŁ</v-btn>
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
@import "../../styles/style_event_details.css";
@import "../../styles/style_global.css";
</style>
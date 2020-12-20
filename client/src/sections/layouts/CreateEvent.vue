<template>
  <v-app id="create_event_app" class="global_app">
    <sidebar/>
    <div id="create_event_div" class="global_div">
      <p id="create_event_caption" class="global_caption">Utwórz wydarzenie</p>


      <v-layout justify-center>
        <v-form class="global_card">
          <p id="create_event_p" class="global_p">rodzaj wydarzenia:</p>
          <select id="create_event_type">
            <option>trening</option>
            <option>sparing</option>
            <option>mecz</option>
            <option>inny</option>
          </select>

          <p id="create_event_p" class="global_p">data:</p>
          <input type="date" id="create_event_date" placeholder="2020-01-01">

          <p id="create_event_p" class="global_p">godzina rozpoczęcia:</p>
          <input type="time" id="create_event_start_time" placeholder="12:00">

          <p id="create_event_p" class="global_p">godzina zakończenia:</p>
          <input type="time" id="create_event_end_time" placeholder="14:00">

          <p id="create_event_p" class="global_p">adres:</p>
          <div class="global_div_centerize">
            <input id="create_event_address" class="global_full_width_input">
          </div>

          <p id="create_event_p" class="global_p">szczegóły:</p>
          <div class="global_div_centerize">
            <textarea id="create_event_details" class="global_full_width_input"></textarea>
          </div>

          <p id="create_event_p" class="global_p">uczestnicy:</p>
          <div class="global_div_centerize">
            <textarea id="create_event_participants" class="global_full_width_input"></textarea>
          </div>

          <div class="global_div_centerize">
            <v-btn id="create_event_button_create" class="global_v_btn" to="/EventDetails">UTWÓRZ</v-btn>
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
@import "../../styles/style_create_event.css";
</style>
<template>
  <v-app id="all_events_app" class="global_app">
    <sidebar/>
    <div id="all_events_div" class="global_div">
      <p id="all_events_caption" class="global_caption">Wszystkie wydarzenia</p>
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
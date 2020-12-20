<template>
  <v-app id="your_events_app" class="global_app">
    <sidebar/>
    <div id="your_events_div" class="global_div">
      <p id="your_events_caption" class="global_caption">Twoje wydarzenia</p>
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
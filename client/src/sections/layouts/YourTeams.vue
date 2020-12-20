<template>
  <v-app id="your_teams_app" class="global_app">
    <sidebar/>
    <div id="your_teams_div" class="global_div">
      <p id="your_teams_caption" class="global_caption">Twoje drużyny</p>
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
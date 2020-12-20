<template>
  <v-app id="all_teams_app" class="global_app">
    <sidebar/>
    <div id="all_teams_div" class="global_div">
      <p id="all_teams_caption" class="global_caption">Wszystkie drużyny</p>
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
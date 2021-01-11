<template>

  <v-app class="global_app">
    <p id="event_details_caption" class="global_caption">Szczegóły wydarzenia</p>
    <v-card
            class="mx-auto ma-5"
            min-width="80%"
            padding="20px"
            
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                  v-text="currentEvent.city"
                  
                ></v-card-title>

                <v-card-subtitle ></v-card-subtitle>

                <v-card-actions>
                  <v-btn
                   
                    class="ml-2 mt-3"
                    fab
                    icon
                    height="40px"
                    right
                    width="40px"
                  >
                    <v-icon>mdi-play</v-icon>
                  </v-btn>

                  <v-btn
                   
                    class="ml-2 mt-5"
                    outlined
                    rounded
                    small
                  >
                    START RADIO
                  </v-btn>
                </v-card-actions>
              </div>

              <v-avatar
                class="ma-3"
                size="125"
                tile
              >
                <v-img></v-img>
              </v-avatar>
            </div>
          </v-card>
  </v-app> 

</template>





 <script>


export default {
  name: 'EventOverview',
  data(){
      return{
          eventValue: JSON.parse(localStorage.getItem('event')) || '',
      };
  },
  computed:{
      currentEvent(){
          return this.eventValue;
      },
      currentUser() {
      return this.$store;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
  
};

</script>
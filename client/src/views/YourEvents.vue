<template>

  <v-app class="global_app">
           <v-card
            v-for="{address, city, id} in events"
            :key="id"
            class="mx-auto ma-5"
            max-width="60%"
            padding="20px"
        >
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="200px"
          ></v-img>
      
          <v-card-title v-if="loaded">
            {{city}}
          </v-card-title>
      
          <v-card-subtitle>
           {{address}}
          </v-card-subtitle>
      
          <v-card-actions>
            <v-btn
              color="orange lighten-2"
              text
              @click="enterEventInfo(id)"
            >
              Przeglądaj
            </v-btn>
      
            <v-spacer></v-spacer>
      
            <v-btn
              icon
              @click="show = !show"
            >
              <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
          </v-card-actions>
      
          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>
      
              <v-card-text>
                I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
              </v-card-text>
            </div>
          </v-expand-transition>
        </v-card>
     
  </v-app>

</template>





<script>
import UserService from '../services/user.service';
import EventService from '../services/event.service';

export default {
  name: 'YourEvents',
  data(){
    return{
      userValue: this.$store.state.auth.user || '',
      events: '',
      loaded: false,
      show: false,
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods:{
    enterEventInfo(id){
      console.log(id)
      EventService.getEvent(id)
      this.$router.push('/eventOverview');
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    UserService.getEvents(this.currentUser.id).then(
        (data) => {
              this.events = data;
              this.loaded = true
              console.log(this.events)
            })
  }
};
//UserService.getEvents(this.userValue.id)
//{{events[0].city}}
</script>
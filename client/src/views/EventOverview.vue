<template>
  <v-app class="global_app">
    <p id="event_details_caption" class="global_caption">
      Szczegóły wydarzenia
    </p>
    <v-card class="mx-auto ma-5" max-width="80%" min-width="80%" padding="20px" elevation="12">
      <div class="d-flex flex-no-wrap justify-space-between">
        <div>
          <v-card-title
            class="headline"
            v-text="currentEvent.city"
          ></v-card-title>

          <v-card-subtitle v-text="currentEvent.address"></v-card-subtitle>

          <v-card-actions>
            <v-btn class="ml-2 mt-3" fab icon height="40px" right width="40px">
              <v-icon>mdi-play</v-icon>
            </v-btn>

            <v-btn class="ml-2 mt-5" outlined rounded small>
              START RADIO
            </v-btn>
          </v-card-actions>
        </div>

        <div>
          <v-card-text  v-text="currentEvent.description">
            
          </v-card-text>
        </div>
      </div>
    </v-card>

    <!-- END OF CARD DETAIL -->
    <!-- START OF CARD ORGANIZER -->

    

    <!-- END OF CARD DETAIL -->
    <!-- START OF CARD ORGANIZER -->
    <!-- <v-avatar color="indigo" size="36">
                <span class="white--text headline">{{ getInitials }}</span>
              </v-avatar> -->
    <p id="event_details_caption" class="global_caption">Uczestnicy</p>
    <v-card 
        class="mx-auto ma-1" 
        min-width="80%" 
        padding="20px" 
        v-for="{name, position, dob, id} in currentEvent.participants"
        :key="id"
        elevation="12">
      <v-row >

         <v-col class="hidden-sm-and-down" align="center" justify="center">
         
            <v-avatar color="indigo ma-5" size="50">
                <span class="white--text headline">{{ getInitials(name) }}</span>
            </v-avatar> 
      
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>Imię i Nazwisko</v-card-title>

          <v-card-text v-text="name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>Pozycja</v-card-title>

          <v-card-text v-text="position"></v-card-text>
        </v-col>

        <v-col class="hidden-sm-and-down">
          <v-card-title>Wiek</v-card-title>

          <v-card-text v-text="calculateAge(dob)"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap" v-if="isAdmin">
          <v-btn  
            color="error ma-5"
            large
            dark
            >
            Usuń
          </v-btn>
        </v-col>
      </v-row>
    </v-card>
    <!-- END OF CARD DETAIL -->
    <!-- START OF CARD ORGANIZER -->


    


    <p id="event_details_caption" class="global_caption">Organizator</p>
    <v-card class="mx-auto ma-5" min-width="80%" padding="20px" elevation="12">
      <v-row>
        <v-col class="text-no-wrap">
          <v-card-title> Imię i Nazwisko:</v-card-title>

          <v-card-text v-text="currentEvent.organizer_id.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title> Kontakt: </v-card-title>

          <v-card-text v-text="currentEvent.organizer_id.email"></v-card-text>
        </v-col>
      </v-row>
    </v-card>
  </v-app>
</template>





 <script>
export default {
  name: 'EventOverview',
  data() {
    return {
      eventValue: JSON.parse(localStorage.getItem('event')) || '',
      lorem: 'dfjasofjdasofj adsofj asdfj asdfj asdjf iasjdf asidjfisdajf asd',
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      isAdmin: false,
    };
  },
  computed: {
    currentEvent() {
      return this.eventValue;
    },
    currentUser() {
      
      return this.userValue;
    },
  },
  methods:{
    calculateAge(birthday) {
          let currentDate = new Date();
          let birthDate = new Date(birthday);
          let difference = currentDate - birthDate;
          let age = Math.floor(difference/31557600000);
          return age
    },
    getInitials(name) {
      return name
        .split(' ')
        .map((n) => n[0])
        .join('');
    },
  },
  
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    console.log(this.currentUser.id)
    console.log(this.currentEvent.organizer_id.id)
    if(this.currentUser.id==this.currentEvent.organizer_id.id)
    { 

      this.isAdmin = true
    }
  },
};
</script>
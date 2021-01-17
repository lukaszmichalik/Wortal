<template>
  <v-app class="global_app">
    <p id="event_details_caption" class="global_caption">
      Szczegóły wydarzenia
    </p>
    <v-card
      class="mx-auto ma-5"
      max-width="80%"
      min-width="80%"
      padding="20px"
      elevation="12"
    >
      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-map-marker</v-icon>
        adres:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="currentEvent.city + ', ' + currentEvent.address"
      ></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-clock-time-two</v-icon>data i
        godzina:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="currentEvent.date + ', ' + currentEvent.time"
      ></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-texture-box</v-icon>rodzaj
        nawierzchni:</v-card-title
      >

      <v-card-text class="title" v-text="currentEvent.surface"></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-information</v-icon>informacje
        dla uczestników:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="currentEvent.description"
      ></v-card-text>

      <v-card-title class="headline"
        ><v-icon color="primary" class="mr-3">mdi-account</v-icon>limit
        uczestników:</v-card-title
      >

      <v-card-text
        class="title"
        v-text="
          this.participantsIds.length.toString() + '/' + currentEvent.limitation
        "
      ></v-card-text>

      <v-card-text
        class="red--text"
        v-if="participantsIds.length == currentEvent.limitation"
        align="center"
      >
        pełny skład</v-card-text
      >

      <v-card-actions>
        <v-btn
          v-if="!isAdmin && participantsIds.length < currentEvent.limitation"
          class="mx-auto"
          :color="participantsIds.includes(currentUser.id) ? 'error' : 'green'"
          large
          text
          @click.once="joinOrGiveUp()"
        >
          {{
            participantsIds.includes(currentUser.id) ? 'zrezygnuj' : 'dołącz'
          }}
          <v-icon
            :color="
              participantsIds.includes(currentUser.id) ? 'error' : 'green'
            "
            right
            >{{
              participantsIds.includes(currentUser.id)
                ? 'mdi-account-minus'
                : 'mdi-account-plus'
            }}</v-icon
          >
        </v-btn>
      </v-card-actions>

      <!-- <v-card-actions>
        <v-btn class="ml-2 mt-3" fab icon height="40px" right width="40px">
          <v-icon>mdi-play</v-icon>
        </v-btn>

        <v-btn class="ml-2 mt-5" outlined rounded small> START RADIO </v-btn>
      </v-card-actions> -->
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
      max-width="80%"
      padding="20px"
      v-for="{ name, position, dob, id } in currentEvent.participants"
      :key="id"
      elevation="12"
    >
      <v-row>
        <v-col class="hidden-sm-and-down" align="center" justify="center">
          <v-avatar color="indigo ma-5" size="50">
            <span class="white--text headline">{{ getInitials(name) }}</span>
          </v-avatar>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>imię i nazwisko</v-card-title>

          <v-card-text v-text="name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>pozycja</v-card-title>

          <v-card-text v-text="position"></v-card-text>
        </v-col>

        <v-col class="hidden-sm-and-down">
          <v-card-title>wiek</v-card-title>

          <v-card-text v-text="calculateAge(dob) + ' lat'"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap" v-if="isAdmin">
          <v-btn color="error ma-8" large dark> Usuń </v-btn>
        </v-col>
      </v-row>
    </v-card>
    <!-- END OF CARD DETAIL -->
    <!-- START OF CARD ORGANIZER -->

    <p id="event_details_caption" class="global_caption">Organizator</p>
    <v-card
      class="mx-auto mb-12"
      min-width="80%"
      max-width="80%"
      padding="20px"
      elevation="12"
    >
      <v-row>
        <v-col class="text-no-wrap">
          <v-card-title> imię i nazwisko:</v-card-title>

          <v-card-text v-text="currentEvent.organizer_id.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap flex-sm">
          <v-card-title> kontakt: </v-card-title>

          <v-card-text v-text="currentEvent.organizer_id.email"></v-card-text>
        </v-col>
      </v-row>
    </v-card>
    <v-btn v-if="isAdmin" class="mx-auto my-8" color="error"
      >Odwołaj wydarzenie</v-btn
    >
  </v-app>
</template>





 <script>
import UserService from '../services/user.service';

export default {
  name: 'EventOverview',
  data() {
    return {
      eventValue: JSON.parse(localStorage.getItem('event')) || '',
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      isAdmin: false,
      participantsIds: [],
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
  methods: {
    calculateAge(birthday) {
      let currentDate = new Date();
      let birthDate = new Date(birthday);
      let difference = currentDate - birthDate;
      let age = Math.floor(difference / 31557600000);
      return age;
    },
    getInitials(name) {
      return name
        .split(' ')
        .map((n) => n[0])
        .join('');
    },
    joinOrGiveUp() {
      UserService.addUserToEvent(this.currentUser.id, this.currentEvent.id);
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    // console.log(this.currentUser.id);
    // console.log(this.currentEvent.organizer_id.id);
    if (this.currentUser.id == this.currentEvent.organizer_id.id) {
      this.isAdmin = true;
    }

    for (let i = 0; i < this.currentEvent.participants.length; i++) {
      this.participantsIds.push(this.currentEvent.participants[i].id);
    }
  },
};
</script>
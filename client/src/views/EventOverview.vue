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
        v-text="formatDate(currentEvent.date)+ ', ' + currentEvent.time"
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
        v-if="
          participantsIds.length == currentEvent.limitation &&
          !participantsIds.includes(currentUser.id)
        "
        align="center"
      >
        pełny skład</v-card-text
      >

      <v-card-actions>
        <v-btn
          v-if="!isAdmin && hasAccess"
          class="mx-auto"
          :color="participantsIds.includes(currentUser.id) ? 'error' : 'green'"
          :loading="loading"
          large
          text
          @click="joinOrGiveUp()"
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
    </v-card>

    <p id="event_details_caption" class="global_caption">Uczestnicy</p>
    <v-card
      class="mx-auto ma-1"
      min-width="80%"
      max-width="80%"
      padding="20px"
      v-for="participant in currentEvent.participants"
      :key="participant.id"
      elevation="12"
    >
    
      <v-row>
        <v-col class="hidden-sm-and-down" align="center">
          <v-avatar color="indigo ma-5" size="50">
            <span class="white--text headline">{{
              getInitials(participant.name)
            }}</span>
          </v-avatar>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>imię i nazwisko</v-card-title>

          <v-card-text v-text="participant.name"></v-card-text>
        </v-col>

        <v-col class="text-no-wrap">
          <v-card-title>pozycja</v-card-title>

          <v-card-text v-text="participant.position"></v-card-text>
        </v-col>

        <v-col class="hidden-sm-and-down" >
          <v-card-title>wiek</v-card-title>

          <v-card-text align="left"
            v-text="calculateAge(participant.dob) + ' lat'"
          ></v-card-text>
        </v-col>

        <v-col class="text-no-wrap" >
          <v-btn
            v-if="isAdmin && participant.id!=currentUser.id"
            color="error ma-8"
            :loading="
              loadingDelParticipant && selectedDelBtns.includes(participant.id)
            "
            large
            dark
            @click.once="deleteParticipant(participant.id)"
          >
            Usuń
          </v-btn>
        </v-col>
      </v-row>
      
    </v-card>

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
    <v-dialog transition="dialog-bottom-transition" max-width="600">
      <template v-slot:activator="{ on, attrs }">
          <v-btn
            v-if="isAdmin"
          class="mx-auto my-8"
          color="error"
            v-bind="attrs"
            v-on="on"
          >Odwołaj wydarzenie</v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="error"
              dark
            >Czy na pewno chcesz usunąć wydarzenie ?</v-toolbar>
            <v-card-text>
              <div class="text-h5 pa-12">Tej akcji nie można cofnąć, czy na pewno chcesz usunąć to wydarznie?</div>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn
                text
                @click="dialog.value = false"
              >Anuluj</v-btn>
               <v-btn
                text
                color="error"
                @click="deleteEvent(currentEvent.id)"
              >Tak, Usuń</v-btn>
            </v-card-actions>
          </v-card>
        </template>
    </v-dialog>
  </v-app>
</template>


 <script>
import EventService from '../services/event.service';
import DateFormatter from '../utils/dateFormatter';
import CalculateAge from '../utils/calculateAge';

export default {
  name: 'EventOverview',
  data() {
    return {
      eventValue: JSON.parse(localStorage.getItem('event')) || {},
      userValue: JSON.parse(localStorage.getItem('user')) || {},
      isAdmin: false,
      participantsIds: [],
      loading: false,
      loadingDelParticipant: false,
      hasAccess: true,
      selectedDelBtns: [],
    };
  },
  computed: {
    currentEvent: {
      get: function () {
        return this.eventValue;
      },
      set: function (event) {
        this.eventValue = event;
        localStorage.setItem('event', JSON.stringify(event));
      },
    },
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    formatDate(date) {
     return DateFormatter.formatDate(date)
    },
    calculateAge(userBirthday) {
      return CalculateAge.calculateAge(userBirthday)
    },
    getInitials(name) {
      return name
        .split(' ')
        .map((n) => n[0])
        .join('');
    },
    joinOrGiveUp() {
      var that = this;

      this.loading = true;

      if (!this.participantsIds.includes(this.currentUser.id)) {
        EventService.addUserToEvent(this.currentUser.id, this.currentEvent.id);

        setTimeout(function () {
          EventService.getEvent(that.currentEvent.id);
        }, 600);

        setTimeout(function () {
          that.currentEvent = JSON.parse(localStorage.getItem('event'));
          that.participantsIds.push(that.currentUser.id);
          that.loading = false;
        }, 1200);
      } else {
        EventService.deleteUserFromEvent(
          this.currentUser.id,
          this.currentEvent.id
        );

        setTimeout(function () {
          EventService.getEvent(that.currentEvent.id);
        }, 600);

        setTimeout(function () {
          that.currentEvent = JSON.parse(localStorage.getItem('event'));
          let index = that.participantsIds.indexOf(that.currentUser.id);
          if (index >= 0) {
            that.participantsIds.splice(index, 1);
          }
          that.loading = false;
        }, 1200);
      }
    },
    deleteParticipant(participantId) {
      this.selectedDelBtns.push(participantId);
      this.loadingDelParticipant = true;
      var that = this;
      EventService.deleteUserFromEvent(participantId, this.currentEvent.id);

      setTimeout(function () {
        EventService.getEvent(that.currentEvent.id);
      }, 600);

      setTimeout(function () {
        that.currentEvent = JSON.parse(localStorage.getItem('event'));
        let index = that.participantsIds.indexOf(participantId);
        if (index >= 0) {
          that.participantsIds.splice(index, 1);
        }
        that.loadingDelParticipant = false;
      }, 1200);
    },

    deleteEvent(id) {
      EventService.deleteEvent(id);
      this.$router.push('/yourEvents')
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    if (this.currentUser.id == this.eventValue.organizer_id.id) {
      this.isAdmin = true;
    }

    for (let i = 0; i < this.currentEvent.participants.length; i++) {
      this.participantsIds.push(this.eventValue.participants[i].id);
    }

    if (
      this.participantsIds.length == this.currentEvent.limitation &&
      !this.participantsIds.includes(this.currentUser.id)
    ) {
      this.hasAccess = false;
    }
  },
};
</script>
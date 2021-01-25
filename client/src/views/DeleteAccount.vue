<template>
  <v-app id="delete_account_app">
    <div id="delete_account_div" class="global_div">
      <div id="delete_account_caption">
        Czy naprawdę chcesz usunąć swoje konto z serwisu? Utracisz dostęp do
        wszystkich swoich wydarzeń i drużyn. Tej czynności nie można cofnąć.
      </div>

      <br />
      <v-btn
        :loading="loading"
        id="delete_account_button_yes"
        class="global_v_btn"
        @click.once="deleteUser"
        >TAK, USUŃ MOJE KONTO</v-btn
      >
      <br />
      <v-btn
        id="delete_account_button_no"
        class="global_v_btn"
        to="/UserProfile"
        >NIE, ZABIERZ MNIE STĄD</v-btn
      >
    </div>
  </v-app>
</template>





<script>
import UserService from '../services/user.service';
import TeamService from '../services/team.service';

export default {
  name: 'DeleteAccount',
  data() {
    return {
      userValue: JSON.parse(localStorage.getItem('user')) || '',
      userManagedTeamsIds: [],
      loading: false,
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    deleteUser() {
      this.loading = true;

      for (let i = 0; i < this.userManagedTeamsIds.length; i++) {
        TeamService.deleteTeam(this.userManagedTeamsIds[i]);
      }

      var that = this;
      setTimeout(function () {
        UserService.deleteUser(that.currentUser.id);
        that.$store.dispatch('auth/logout');
        that.$router.push('/home');
        that.loading = false;
      }, 500);
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else {
      TeamService.getUserManagedTeams(this.currentUser.id).then((data) => {
        for (let i = 0; i < data.length; i++) {
          this.userManagedTeamsIds.push(data[i].id);
        }
      });
    }
  },
};
</script>





<style>
@import '../styles/style_delete_account.css';
@import '../styles/style_global.css';
</style>
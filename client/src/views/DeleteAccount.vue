<template>
  <v-app id="delete_account_app">
  <!--<div id="delete_account_div_background">
    <div id="delete_account_space" />!-->
    <div id="delete_account_div" class="global_div">
      <div id="delete_account_caption">
        Czy naprawdę chcesz usunąć swoje konto z serwisu? Utracisz dostęp do
        wszystkich swoich wydarzeń i drużyn. Tej czynności nie można cofnąć.
      </div>

      <br />
      <v-btn id="delete_account_button_yes" class="global_v_btn" @click.once="deleteUser"
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
  <!--</div>!-->
</template>


<script>
import UserService from '../services/user.service';

export default {
  
  
  name: 'DeleteAccount',
  data(){
    return {
      userValue: JSON.parse(localStorage.getItem('user')) || '',
    };
  },
  computed: {
    currentUser() {
      return this.userValue;
    },
  },
  methods: {
    deleteUser() {
      UserService.deleteUser(this.currentUser.id)
      this.$store.dispatch('auth/logout');
      this.$router.push('/home');
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },

}
</script>


<style>
@import '../styles/style_delete_account.css';
@import '../styles/style_global.css';
</style>
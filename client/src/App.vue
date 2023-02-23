<template>
  <v-app>
    <div id="app">
      <nav
        v-if="currentUser"
        class="navbar navbar-expand navbar-dark"
        style="background-color: #2196f3"
      >
        <v-app-bar-nav-icon
          id="toolbar_icon"
          @click="drawer = !drawer"
        ></v-app-bar-nav-icon>
        <v-toolbar-title id="toolbar_title">Wortal piłkarski</v-toolbar-title>

        <div v-if="currentUser" class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" @click.prevent="logout">WYLOGUJ </a>
          </li>
          <v-navigation-drawer
            app
            v-model="drawer"
            temporary
            id="navigation_drawer"
          >
            <v-col>
              <v-btn id="navigation_drawer_v_btn_blue" to="/YourEvents"
                >Twoje wydarzenia</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_blue" to="/AllEvents"
                >Wszystkie wydarzenia</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_blue" to="/CreateEvent"
                >Utwórz wydarzenie</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_green" to="/yourTeams"
                >Twoje drużyny</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_green" to="/AllTeams"
                >Wszystkie drużyny</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_green" to="/createTeam"
                >Utwórz drużynę</v-btn
              >
              <v-btn id="navigation_drawer_v_btn_red" to="/UserProfile"
                >Profil użytkownika</v-btn
              >
              <v-btn
                v-if="showAdminBoard"
                id="navigation_drawer_v_btn_red"
                to="/admin"
                >Panel administratora</v-btn
              >
            </v-col>
          </v-navigation-drawer>
        </div>
      </nav>

      <v-main>
        <v-container fluid class="custom-container container-fluid">
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </v-container>
      </v-main>
    </div>
  </v-app>
</template>





<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      drawer: false,
      loading: false,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }else{
        return false;
      }
    },
  },
  methods: {
    logout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/home');
    },
  },
};
</script>





<style>
@import './styles/style_sidebar.css';

.container-fluid.custom-container {
  padding: 0px;
  min-width: 100%;
}
v-btn:hover {
  opacity: 0.5;
}
</style>
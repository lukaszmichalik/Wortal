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
          <!-- umieszcza LOGOUT po lewej stronie toolbara !-->
          <li class="nav-item">
            <a class="nav-link" @click.prevent="logout">
              <!--<font-awesome-icon icon="sign-out-alt" />!-->WYLOGUJ
            </a>
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
              <v-btn id="navigation_drawer_v_btn_green" to="/TeamOverview"
                >Twoja drużyna</v-btn
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
            </v-col>
          </v-navigation-drawer>
        </div>
      </nav>

      <v-main>
      <v-container  fluid class="custom-container container-fluid">
        <transition name="fade" mode="out-in" >
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
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
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
};
v-btn:hover{
  opacity: 0.5;
};

</style>
























<!--<template>
  <v-app id="app">
    <nav class="navbar navbar-expand navbar-blue bg-dark">
      <v-app-bar-nav-icon v-if="currentUser" id="toolbar_icon"  @click="drawer = !drawer"></v-app-bar-nav-icon>
      <a href class="navbar-brand" @click.prevent>BallWortal</a>
      <!-- <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home" />Home
          </router-link>
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/admin" class="nav-link">Admin Board</router-link>
        </li>
        <li v-if="showModeratorBoard" class="nav-item">
          <router-link to="/mod" class="nav-link">Moderator Board</router-link>
        </li> -->
        <!-- <li class="nav-item">
          <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
        </li> -->
      <!-- </div> -->

      <!--<div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </li>
        <v-navigation-drawer app v-model="drawer" temporary id="navigation_drawer">
            
            <v-col>
              <v-btn id="navigation_drawer_button_your_events" to="/YourEvents" class="navigation_drawer_v_btn_blue">Twoje wydarzenia</v-btn>
              <v-btn id="navigation_drawer_button_all_events" to="/AllEvents" class="navigation_drawer_v_btn_blue">Wszystkie wydarzenia</v-btn>
              <v-btn id="navigation_drawer_button_create_event" to="/CreateEvent" class="navigation_drawer_v_btn_blue">Utwórz wydarzenie</v-btn>
              <v-btn id="navigation_drawer_button_your_teams" to="/YourTeams" class="navigation_drawer_v_btn_green">Twoje drużyny</v-btn>
              <v-btn id="navigation_drawer_button_all_teams" to="/AllTeams" class="navigation_drawer_v_btn_green">Wszystkie drużyny</v-btn>
              <v-btn id="navigation_drawer_button_create_team" to="/CreateTeam" class="navigation_drawer_v_btn_green">Utwórz drużynę</v-btn>
              <v-btn id="navigation_drawer_button_user_profile" to="/Profile" class="navigation_drawer_v_btn_red">Profil użytkownika</v-btn>
              <v-btn id="navigation_drawer_button_login" to="/Logout" class="navigation_drawer_v_btn_red">Wyloguj się</v-btn>
            </v-col>
        </v-navigation-drawer>
      </div>
    </nav>

    <v-main>
      <v-container  fluid class="custom-container container-fluid">
        <transition name="fade" mode="out-in" >
          <router-view></router-view>
        </transition>
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
import "./styles/style_sidebar.css";
export default {
  name: "Sidebar",
    data() {
        return {
            drawer: false
        };
    },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR');
      }

      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style type="text/css">
    .container-fluid.custom-container {
      padding: 0px;
      min-width: 100%;
    };

    v-btn:hover{
      opacity: 0.5;
    };
</style>
!-->

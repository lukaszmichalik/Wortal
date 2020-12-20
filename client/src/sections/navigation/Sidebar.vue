<template>
    <div>
        <v-app-bar fixed dense color="blue" height="70px">
            <v-app-bar-nav-icon id="toolbar_icon" @click="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title id="toolbar_title">Nazwa wortalu</v-toolbar-title>
        </v-app-bar>

        <v-content>
            <slot/>
        </v-content>

        <v-navigation-drawer app v-model="drawer" temporary id="navigation_drawer">
            <br>
            <v-btn id="navigation_drawer_button_your_events" to="/YourEvents" class="navigation_drawer_v_btn_blue">Twoje wydarzenia</v-btn>
            <v-btn id="navigation_drawer_button_all_events" to="/AllEvents" class="navigation_drawer_v_btn_blue">Wszystkie wydarzenia</v-btn>
            <v-btn id="navigation_drawer_button_create_event" to="/CreateEvent" class="navigation_drawer_v_btn_blue">Utwórz wydarzenie</v-btn>
            <v-btn id="navigation_drawer_button_your_teams" to="/YourTeams" class="navigation_drawer_v_btn_green">Twoje drużyny</v-btn>
            <v-btn id="navigation_drawer_button_all_teams" to="/AllTeams" class="navigation_drawer_v_btn_green">Wszystkie drużyny</v-btn>
            <v-btn id="navigation_drawer_button_create_team" to="/CreateTeam" class="navigation_drawer_v_btn_green">Utwórz drużynę</v-btn>
            <v-btn id="navigation_drawer_button_user_profile" to="/UserProfile" class="navigation_drawer_v_btn_red">Profil użytkownika</v-btn>
            <v-btn id="navigation_drawer_button_login" to="/Logout" class="navigation_drawer_v_btn_red">Wyloguj się</v-btn>
        </v-navigation-drawer>

    </div>
</template>





<script>
import "../../styles/style_sidebar.css";

export default {
    name: "Sidebar",
    data() {
        return {
            drawer: false
        };
    }
}

</script>
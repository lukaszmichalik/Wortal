import Vue from 'vue'
import VueRouter from 'vue-router'
import YourEvents from './sections/layouts/YourEvents.vue'
import AllEvents from './sections/layouts/AllEvents.vue'
import CreateEvent from './sections/layouts/CreateEvent.vue'
import YourTeams from './sections/layouts/YourTeams.vue'
import AllTeams from './sections/layouts/AllTeams.vue'
import CreateTeam from './sections/layouts/CreateTeam.vue'
import UserProfile from './sections/layouts/UserProfile.vue'
import Login from './sections/authentication/Login.vue'
import Register from './sections/authentication/Register.vue'
import EventDetails from './sections/layouts/EventDetails.vue'
import TeamDetails from './sections/layouts/TeamDetails.vue'

Vue.use(VueRouter)



  const router = new VueRouter({
      mode: 'history',
      base: process.env.BASE_URL,
      scrollBehavior: () => ({y: 0}),
      routes: [
        {
          path: '/',
          name: 'login',
          component: Login
        },
        {
          path: '/YourEvents',
          name: 'yourevents',
          component: YourEvents
        },
        {
          path: '/AllEvents',
          name: 'allevents',
          component: AllEvents
        },
        {
          path: '/CreateEvent',
          name: 'createevent',
          component: CreateEvent
        },
        {
          path: '/YourTeams',
          name: 'yourteams',
          component: YourTeams
        },
        {
          path: '/AllTeams',
          name: 'allteams',
          component: AllTeams
        },
        {
          path: '/CreateTeam',
          name: 'createteam',
          component: CreateTeam
        },
        {
          path: '/UserProfile',
          name: 'userprofile',
          component: UserProfile
        },
        {
          path: '/Logout',
          name: 'logout',
          component: Login
        },
        {
          path: '/Register',
          name: 'register',
          component: Register
        },
        {
          path: '/EventDetails',
          name: 'eventdetails',
          component: EventDetails
        },
        {
          path: '/TeamDetails',
          name: 'teamdetails',
          component: TeamDetails
        }
      ]
  })

  export default router
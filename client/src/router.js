import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import Welcome from './views/Welcome.vue';
import DeleteAccount from './views/DeleteAccount';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/welcome',
      component: Welcome
    },
    {
      path: '/userProfile',
      name: 'userProfile',
      component: () => import('./views/UserProfile.vue')
    },
    {
      path: '/editProfile',
      name: 'editProfile',
      component: () => import('./views/EditProfile.vue')
    },
    {
      path: '/yourEvents',
      name: 'yourEvents',
      component: () => import('./views/YourEvents.vue')
    },
    {
      path: '/eventOverview',
      name: 'eventOverview',
      component: () => import('./views/EventOverview.vue')
    },
    {
      path: '/createEvent',
      name: 'createEvent',
      component: () => import('./views/CreateEvent.vue')
    },
    {
      path: '/allEvents',
      name: 'allEvents',
      component: () => import('./views/AllEvents.vue')
    },
    {
      path: '/createTeam',
      name: 'createTeam',
      component: () => import('./views/CreateTeam.vue')
    },
    {
      path: '/teamOverview',
      name: 'teamOverview',
      component: () => import('./views/TeamOverview.vue')
    },
    {
      path: '/allTeams',
      name: 'allTeams',
      component: () => import('./views/AllTeams.vue')
    },
    {
      path: '/yourTeams',
      name: 'yourTeams',
      component: () => import('./views/YourTeams.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/DeleteAccount',
      component: DeleteAccount
    }
  ]
});


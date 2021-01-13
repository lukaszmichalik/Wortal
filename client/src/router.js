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
      path: '/admin',
      name: 'admin',
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/mod',
      name: 'moderator',
      component: () => import('./views/BoardModerator.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('./views/BoardUser.vue')
    },
    {
      path: '/DeleteAccount',
      component: DeleteAccount
    }
  ]
});





















/*import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

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
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/editProfile',
      name: 'editProfile',
      // lazy-loaded
      component: () => import('./views/EditProfile.vue')
    },
    {
      path: '/yourEvents',
      name: 'yourEvents',
      // lazy-loaded
      component: () => import('./views/YourEvents.vue')
    },
    {
      path: '/eventOverview',
      name: 'eventOverview',
      // lazy-loaded
      component: () => import('./views/EventOverview.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/mod',
      name: 'moderator',
      // lazy-loaded
      component: () => import('./views/BoardModerator.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('./views/BoardUser.vue')
    }
  ]
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');

//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });
*/

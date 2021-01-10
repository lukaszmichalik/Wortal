<template>
  <v-app id="register_app">
    <div id="register_div" class="global_div">
      
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">

          <div id="register_caption" class="global_caption">Utwórz konto</div>

          <div class="input">

            <input required
              
              class="global_login_or_register_data_input"
              placeholder="Imię i Nazwisko"
              v-model="user.name"
              v-validate="'required|min:3|max:40'"
              type="text"
              name="name"
            />

            <div
              v-if="submitted && errors.has('name')"
              class="alert-danger input_error"
            >{{errors.first('name')}}</div>
            
          </div>
      
          <div class="input">

            <select required
              
              class="global_login_or_register_data_input"
              v-model="user.position">
              <option disabled value="" selected hidden>Pozycja</option>
              <option value="Napastnik">Napastnik</option>
              <option value="Obrońca">Obrońca</option>
              <option value="Bramkarz">Bramkarz</option>
              <option value="Pomocnik">Pomocnik</option>

            </select>
          </div>

          <div class="input">

            <input required
              
              class="global_login_or_register_data_input"
              type="date"
              placeholder="Wiek"
              v-model="user.dob"
              v-validate="'required'"
              name="dob"
            />

          </div>

          <div class="input">

            <input required
              
              class="global_login_or_register_data_input"
              type="email"
              placeholder="Email"
              v-model="user.email"
              v-validate="'required|email|max:30'"
              name="email"
            />

          </div>

          <div class="input">

            <input required
              
              class="global_login_or_register_data_input"
              placeholder="Login"
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              name="username"
            />

            <div class="alert-danger input_error"
                v-if="submitted && errors.has('username')"
                >
  
                {{ errors.first('username') }}
  
            </div>

          </div>


          <div class="input">

            <input required
              id="register_password"
              class="global_login_or_register_data_input"
              type="password"
              placeholder="Hasło"
              v-model="user.password"
              v-validate="'required|min:6|max:30'"
              name="password"
            />

          <div 
            v-if="submitted && errors.has('password')" 
            class="alert-danger input_error"
          >
            
            {{ errors.first('password') }}

          </div>

          </div>
          
          

          <div class="submit">

            <v-btn
              id="register_button_register"
              class="global_v_btn"
              type="submit"
            >

              UTWÓRZ KONTO

            </v-btn>
          </div>
        </div>
      </form>
      <div
        v-if="message"
        class="alert input_error"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </v-app>
</template>

<script>
import User from '../models/user';

export default {
  name: 'Register',
  data() {
    return {
      user: new User('','', '', '', '','',''),
      submitted: false,
      successful: false,
      message: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
            },
            (error) => {
              this.message =
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
  },
};
</script>

<style>
@import '../styles/style_register.css';
@import '../styles/style_global.css';

select:invalid { color: gray; }

option {
  color: black;
}


</style>
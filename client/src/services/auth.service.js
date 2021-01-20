import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('event');
    localStorage.removeItem('team');
    localStorage.removeItem('userTeam');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      name: user.name,
      position: user.position,
      dob: user.dob,
      email: user.email,
      username: user.username,
      password: user.password
    });
  }
}

export default new AuthService();


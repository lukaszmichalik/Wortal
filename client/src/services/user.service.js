import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  editUser(user) {
    return axios.post(API_URL + 'editUser', {

      id: user.id,
      name: user.name,
      position: user.position,
      dob: user.dob,
      email: user.email,
      username: user.username,
      password: user.password

    }).then(response => {

      const newValue = {
        ...JSON.parse(localStorage.getItem("user")),
        id: response.data.id,
        name: response.data.name,
        position: response.data.position,
        dob: response.data.dob,
        email: response.data.email,
        username: response.data.username
     }
    console.log(response);

       localStorage.setItem('user', JSON.stringify(newValue));

    });
  }
}

export default new UserService();

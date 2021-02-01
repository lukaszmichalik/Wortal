import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/user/';

class UserService {

  editUser(user) {
    return axios.post(API_URL + 'editUser', {

      id: user.id,
      name: user.name,
      position: user.position,
      dob: user.dob,
      email: user.email,
      username: user.username,
      password: user.password

    }, { headers: authHeader() }).then(response => {

      const newValue = {
        ...JSON.parse(localStorage.getItem("user")),
        id: response.data.id,
        name: response.data.name,
        position: response.data.position,
        dob: response.data.dob,
        email: response.data.email,
        username: response.data.username
      }

      localStorage.setItem('user', JSON.stringify(newValue));

    });
  }



  deleteUser(id) {
    return axios.post(API_URL + 'deleteUser', {
      id: id
    }, { headers: authHeader() })
  }

  allUsers() {
    return axios.get(API_URL + 'allUsers', { headers: authHeader() }).then(
      response => {
        return Promise.resolve(response.data)
      }, { headers: authHeader() })
  }





}

export default new UserService();

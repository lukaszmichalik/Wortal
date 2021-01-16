import axios from 'axios';

const API_URL = 'http://localhost:8080/api/user/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
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

       localStorage.setItem('user', JSON.stringify(newValue));

    });
  }

  getUserEvents(id){
    return axios.post(API_URL + 'getUserEvents', {
      id: id
    }).then( response =>{
      console.log(response)
      return Promise.resolve(response.data.events)
    })
  }

  deleteUser(id){
    return axios.post(API_URL + 'deleteUser', {
      id: id
    })
  }

  allUsers(){
    return axios.get(API_URL + 'allUsers').then(
      response =>{
        return Promise.resolve(response.data)
      })
  }

  addUserToEvent(userId, eventId){
    return axios.post(API_URL + 'addUserToEvent', {
        userId: userId,
        eventId: eventId
    })
    // .then(response =>{
    //    return Promise.resolve(response.data)
    // })
}

}

export default new UserService();

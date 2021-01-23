import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/team/';

class TeamService {

  createTeam(team) {
    return axios.post(API_URL + 'createTeam', {
      name: team.name,
      location: team.location,
      creationDate: team.creationDate,
      description: team.description,
      manager_id: team.manager_id,
      players: team.players

    },{ headers: authHeader() }).then(response => {
      const newValue = {
        ...JSON.parse(localStorage.getItem("user")),
        team_id: response.data.id,

      }

      localStorage.setItem('user', JSON.stringify(newValue));
    })
  }

  getTeam(id) {
    return axios.post(API_URL + 'getTeam', {
      id: id
    },{ headers: authHeader() }).then(response => {
      localStorage.setItem('team', JSON.stringify(response.data))
    })
  }

  getTeamTest(id) {
    return axios.post(API_URL + 'getTeam', {
      id: id
    },{ headers: authHeader() }).then(response => {
      return Promise.resolve(response.data)
    })
  }


  allTeams() {
    return axios.get(API_URL + 'allTeams',{ headers: authHeader() }).then(
      response => {
        return Promise.resolve(response.data)
      })
  }

  getUserTeams(id) {
    return axios.post(API_URL + 'getUserTeams', {
      id: id
    },{ headers: authHeader() }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  getUserManagedTeams(id) {
    return axios.post(API_URL + 'getUserManagedTeams', {
      id: id
    },{ headers: authHeader() }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  allUsersWithoutTeam() {
    return axios.get(API_URL + 'allUsersWithoutTeam',{ headers: authHeader() }).then(
      response => {
        return Promise.resolve(response.data)
      })
  }

  addUserToTeam(userId, teamId) {
    return axios.post(API_URL + 'addUserToTeam', {
      userId: userId,
      teamId: teamId
    },{ headers: authHeader() })
  }

  deleteUserFromTeam(userId, teamId) {
    return axios.post(API_URL + 'deleteUserFromTeam', {
      userId: userId,
      teamId: teamId
    },{ headers: authHeader() })
  }

  deleteTeam(id) {
    return axios.post(API_URL + 'deleteTeam', {
        id: id
    },{ headers: authHeader() }).then(
        response => {
            return Promise.resolve(response.data)
        })
}


}

export default new TeamService();

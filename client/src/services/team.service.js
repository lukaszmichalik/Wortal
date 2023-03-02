import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/team/';

class TeamService {

  createTeam(team) {
    return axios.post(API_URL + 'create-team', {
      name: team.name,
      location: team.location,
      creationDate: team.creationDate,
      description: team.description,
      manager_id: team.manager_id,
      players: team.players

    }, { headers: authHeader() }).then(response => {
      const newValue = {
        ...JSON.parse(localStorage.getItem("user")),
        team_id: response.data.id,

      }

      localStorage.setItem('user', JSON.stringify(newValue));
    })
  }

  getTeam(id) {
    return axios.post(API_URL + 'get-team', {
      id: id
    }, { headers: authHeader() }).then(response => {
      localStorage.setItem('team', JSON.stringify(response.data))
    })
  }

  getTeamTest(id) {
    return axios.post(API_URL + 'get-team', {
      id: id
    }, { headers: authHeader() }).then(response => {
      return Promise.resolve(response.data)
    })
  }


  allTeams() {
    return axios.get(API_URL + 'get-all-teams', { headers: authHeader() }).then(
      response => {
        return Promise.resolve(response.data)
      })
  }

  getUserTeams(id) {
    return axios.post(API_URL + 'get-user-teams', {
      id: id
    }, { headers: authHeader() }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  getTeamsManagedByUser(id) {
    return axios.post(API_URL + 'get-teams-managed-by-user', {
      id: id
    }, { headers: authHeader() }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  addUserToTeam(userId, teamId) {
    return axios.post(API_URL + 'add-user-to-team', {
      userId: userId,
      teamId: teamId
    }, { headers: authHeader() })
  }

  deleteUserFromTeam(userId, teamId) {
    return axios.post(API_URL + 'delete-user-from-team', {
      userId: userId,
      teamId: teamId
    }, { headers: authHeader() })
  }

  deleteTeam(id) {
    return axios.post(API_URL + 'delete-team', {
      id: id
    }, { headers: authHeader() }).then(
      response => {
        return Promise.resolve(response.data)
      })
  }


}

export default new TeamService();

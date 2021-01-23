import axios from 'axios';

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

    }).then(response => {
      // return Promise.resolve(response.data.message)
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
    }).then(response => {
      localStorage.setItem('team', JSON.stringify(response.data))
    })
  }

  getTeamTest(id) {
    return axios.post(API_URL + 'getTeam', {
      id: id
    }).then(response => {
      return Promise.resolve(response.data)
    })
  }


  allTeams() {
    return axios.get(API_URL + 'allTeams').then(
      response => {
        return Promise.resolve(response.data)
      })
  }

  getUserTeams(id) {
    return axios.post(API_URL + 'getUserTeams', {
      id: id
    }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  getUserManagedTeams(id) {
    return axios.post(API_URL + 'getUserManagedTeams', {
      id: id
    }).then(response => {
      return Promise.resolve(response.data.teams)
    })
  }

  allUsersWithoutTeam() {
    return axios.get(API_URL + 'allUsersWithoutTeam').then(
      response => {
        return Promise.resolve(response.data)
      })
  }

  addUserToTeam(userId, teamId) {
    return axios.post(API_URL + 'addUserToTeam', {
      userId: userId,
      teamId: teamId
    })
  }

  deleteUserFromTeam(userId, teamId) {
    return axios.post(API_URL + 'deleteUserFromTeam', {
      userId: userId,
      teamId: teamId
    })
  }

  deleteTeam(id) {
    return axios.post(API_URL + 'deleteTeam', {
        id: id
    }).then(
        response => {
            return Promise.resolve(response.data)
        })
}


}

export default new TeamService();

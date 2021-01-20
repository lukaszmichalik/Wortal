import axios from 'axios';

const API_URL = 'http://localhost:8080/api/team/';

class TeamService {
    
    createTeam(team){
        return axios.post(API_URL + 'createTeam', {
            name:team.name,
            location:team.location,
            creationDate:team.creationDate,
            description:team.description,
            manager_id:team.manager_id,
            players:team.players
           
        }).then(response =>{
            return Promise.resolve(response.data.message)
        })
    }

    getTeam(id){
        return axios.post(API_URL + 'getTeam', {
            id: id
        }).then(response =>{
            localStorage.setItem('team', JSON.stringify(response.data))
        })
    }


    allTeams(){
        return axios.get(API_URL + 'allTeams').then(
          response =>{
            return Promise.resolve(response.data)
          })
    }

}

export default new TeamService();

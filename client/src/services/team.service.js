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

}

export default new TeamService();

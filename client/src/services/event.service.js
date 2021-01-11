import axios from 'axios';

const API_URL = 'http://localhost:8080/api/event/';

class EventService {
    getEvent(id){
        return axios.post(API_URL + 'getEvent', {
            id: id
        }).then(response =>{
            localStorage.setItem('event', JSON.stringify(response.data))
        })
    }

}

export default new EventService();

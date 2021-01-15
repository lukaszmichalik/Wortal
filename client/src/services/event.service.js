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

    createEvent(event){
        return axios.post(API_URL + 'createEvent', {
            city:event.city,
            address:event.address,
            date:event.date,
            time:event.time,
            surface:event.surface,
            limitation:event.limitation,
            description:event.description,
            participants:event.participants,
            organizer_id:event.organizer_id

        }).then(response =>{
            console.log(response)
            // return Promise.resolve(response.data)
        })
    }

}

export default new EventService();

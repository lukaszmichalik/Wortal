import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/event/';

class EventService {
    getEvent(id) {
        return axios.post(API_URL + 'get-event', {
            id: id
        }, { headers: authHeader() }).then(response => {
            localStorage.setItem('event', JSON.stringify(response.data))
        })
    }

    createEvent(event) {
        return axios.post(API_URL + 'create-event', {
            city: event.city,
            address: event.address,
            date: event.date,
            time: event.time,
            surface: event.surface,
            limitation: event.limitation,
            description: event.description,
            participants: event.participants,
            organizer_id: event.organizer_id

        }, { headers: authHeader() }).then(response => {
            return Promise.resolve(response.data.message)
        })
    }

    allEvents() {
        return axios.get(API_URL + 'get-all-events', { headers: authHeader() }).then(
            response => {
                return Promise.resolve(response.data)
            })
    }

    getEventsNotAttendedByUser(id) {
        return axios.post(API_URL + 'get-events-not-attended-by-user', {
            id: id
        }, { headers: authHeader() }).then(
            response => {
                return Promise.resolve(response.data)
            })
    }

    deleteEvent(id) {
        return axios.post(API_URL + 'delete-event', {
            id: id
        }, { headers: authHeader() }).then(
            response => {
                return Promise.resolve(response.data)
            })
    }

    getUserEvents(id) {
        return axios.post(API_URL + 'get-user-events', {
            id: id
        }, { headers: authHeader() }).then(response => {
            return Promise.resolve(response.data.events)
        })
    }

    addUserToEvent(userId, eventId) {
        return axios.post(API_URL + 'add-user-to-event', {
            userId: userId,
            eventId: eventId
        }, { headers: authHeader() })
    }

    deleteUserFromEvent(userId, eventId) {
        return axios.post(API_URL + 'delete-user-from-event', {
            userId: userId,
            eventId: eventId
        }, { headers: authHeader() })
    }

}

export default new EventService();

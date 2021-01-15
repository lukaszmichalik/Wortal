export default class Event {
  constructor(
    city,
    address,
    date,
    time,
    surface,
    limitation,
    description,
    participants,
    organizer_id) {
    this.city = city;
    this.address = address;
    this.date = date;
    this.time = time;
    this.surface = surface;
    this.limitation = limitation;
    this.description = description;
    this.participants = participants;
    this.organizer_id = organizer_id
  }
}

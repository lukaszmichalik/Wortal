class DateFormatter {

  formatDate(date) {
    var formattedDate = new Date(date);
    var months = [
      'stycznia',
      'lutego',
      'marca',
      'kwietnia',
      'maja',
      'czerwca',
      'lipca',
      'sierpnia',
      'września',
      'października',
      'listopada',
      'grudnia',
    ][formattedDate.getMonth()];
    var formattedDateString =
      formattedDate.getDate() + ' ' + months + ' ' + formattedDate.getFullYear();
    return formattedDateString;
  }


  getDayOfWeek(date) {
    var eventDate = new Date(date);
    var daysOfWeek = [
      'niedziela',
      'poniedziałek',
      'wtorek',
      'środa',
      'czwartek',
      'piątek',
      'sobota',
    ][eventDate.getDay()];
    return daysOfWeek;
  }


}

export default new DateFormatter();

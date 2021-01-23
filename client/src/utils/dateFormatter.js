class DateFormatter {
  
    formatDate(date) {
        var expectedDateFormat = new Date(date);
        var expectedMonths = [
          'Styczeń',
          'Luty',
          'Marzec',
          'Kwiecień',
          'Maj',
          'Czerwiec',
          'Lipiec',
          'Sierpień',
          'Wrzesień',
          'Październik',
          'Listopad',
          'Grudzień',
        ][expectedDateFormat.getMonth()];
  
        var expectedDateString =
          expectedDateFormat.getDate() +
          ' ' +
          expectedMonths +
          ' ' +
          expectedDateFormat.getFullYear();
        return expectedDateString;
      }


}

export default new DateFormatter();

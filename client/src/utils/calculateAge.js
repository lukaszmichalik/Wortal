class CalculateAge {
  
    calculateAge(userBirthday) {
        let today = new Date();
        let birthDate = new Date(userBirthday);
        let difference = today - birthDate;
        let userAge = Math.floor(difference / 31557600000);
        return userAge;
      }

}

export default new CalculateAge();

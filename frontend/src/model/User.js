
export default class User {
  constructor(
    firstName=null,
    lastName=null,
    gender=null,
    dateOfBirth=null,
    aadharNumber=null,
    email=null,
    username=null,
    password=null,
    fatherName=null,
    motherName=null,
    category=null,
    accountNumber=null,
    address=null,
    contactNo=null,
    role=null
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.aadharNumber = aadharNumber;
    this.email = email;
    this.username = username;
    this.password = password;
    this.fatherName = fatherName;
    this.motherName = motherName;
    this.category = category;
    this.accountNumber = accountNumber;
    this.address = address;
    this.contactNo = contactNo;
    this.role = role;
  }
}


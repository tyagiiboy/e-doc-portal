export default class School {
  constructor(
        disecode = null, 
        doe = null, 
        name = null, 
        email = null, 
        password = null, 
        contact1 = null, 
        contact2 = null, 
        coed = null, 
        level = null, 
        address = null, 
        affiliation = null, 
        username = null
      ) {
    this.diseCode = disecode;
    this.name = name;
    this.affiliation = affiliation;
    this.establishedDate = doe;
    this.level = level;
    this.address = address;
    this.coEd = coed;
    this.contact1 = contact1;
    this.contact2 = contact2;
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
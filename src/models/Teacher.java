package models;

public class Teacher {
  private String personaNumber;
  private String name;

  public Teacher(String personaNumber, String name) {
    this.personaNumber = personaNumber;
    this.name = name;
  }

  public Teacher() {
  }

  public String getPersonaNumber() {
    return personaNumber;
  }

  public void setPersonaNumber(String personaNumber) {
    this.personaNumber = personaNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toStringTeacher() {
    return this.personaNumber + ',' + this.name;
  }
}

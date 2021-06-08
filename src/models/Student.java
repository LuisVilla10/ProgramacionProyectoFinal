package models;

public class Student {
  private String studentDNI;
  private String name;
  private String date;
  private String idCourseTeacher;

  public Student(String studentDNI, String name, String date, String idCourseTeacher) {
    this.studentDNI = studentDNI;
    this.name = name;
    this.date = date;
    this.idCourseTeacher = idCourseTeacher;
  }

  public Student() {
  }

  public String getStudentDNI() {
    return studentDNI;
  }

  public void setStudentDNI(String studentDNI) {
    this.studentDNI = studentDNI;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getidCourseTeacher() {
    return idCourseTeacher;
  }

  public void setCourseteacher(String idCourseTeacher) {
    this.idCourseTeacher = idCourseTeacher;
  }

  public String toStringStudent() {
    return this.studentDNI + ',' + this.name + ',' + this.date + ',' + this.idCourseTeacher;
  }
}

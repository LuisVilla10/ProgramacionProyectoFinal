package models;

public class CourseTeacher {
  private String idCourse;
  private String  idTeacher;

  public CourseTeacher(String idCourse ,String idTeacher) {
    this.idCourse = idCourse;
    this.idTeacher = idTeacher;
  }

  public CourseTeacher() {
  }

  public String getIdCourse() {
    return idCourse;
  }

  public void setIdCourse(String idCourse) {
    this.idCourse = idCourse;
  }

  public String getIdTeacher() {
    return idTeacher;
  }

  public void setIdTeacher(String idTeacher) {
    this.idTeacher = idTeacher;
  }

  public String toStringCourseTeacher() {
    return idCourse + "," + idTeacher;
  }
}

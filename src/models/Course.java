package models;
public class Course {
  private String idCourse;
  private String name;
  private int score;

  public Course(String idCourse, String name, int score) {
    this.idCourse = idCourse;
    this.name = name;
    this.score = score;
  }
  
  public Course() {
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getIdCourse() {
    return name;
  }

  public void setIdCourse(String name) {
    this.name = name;
  }

  public String getName() {
    return idCourse;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toStringCourse() {
    return this.idCourse + "," + this.name+ "," +this.score;
  }
}

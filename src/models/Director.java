package models;

public class Director {
  private String personalNum;
  private String name;
  private String job;

  public Director(String personalNum,String name, String job) {
    this.name = name;
    this.job = job;
    this.personalNum = personalNum;
  }

  public Director() {
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

 public String getPersonalNum() {
    return this.personalNum;
  }

  public void setPersonalNum(String personalNum) {
    this.personalNum = personalNum;
  }

  public String toStringDirector(){
 	return this.personalNum + "," + this.name + "," + this.job;

  }
}

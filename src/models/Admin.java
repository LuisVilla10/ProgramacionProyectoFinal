package models;

public class Admin {
  private String id;
  private String name;
  private String job;

  public Admin(String id, String name, String job) {
    this.id = id;
    this.name = name;
    this.job = job;
  }

  public Admin() {
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

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String toStringAdmin() {
    return this.id + "," +this.name + "," + this.job;
  }

}

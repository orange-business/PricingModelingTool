package gson_test1;

public class Employee {
  private String userid;
  private long startDate;

  public Employee(String userid, long startDate) {
    this.userid = userid;
    this.startDate = startDate;
  }
  public Employee() {}

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public long getStartDate() {
    return startDate;
  }

  public void setStartDate(long startDate) {
    this.startDate = startDate;
  }
}

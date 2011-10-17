package abid.password.springmvc.model;

import abid.password.parameters.TimeParameter;

public class TimeLockPasswordFormBean {

  private String username;
  private String password;
  private TimeParameter timeParameter;
  private int start;
  private int end;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public TimeParameter getTimeParameter() {
    return timeParameter;
  }

  public void setTimeParameter(TimeParameter timeParameter) {
    this.timeParameter = timeParameter;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

}

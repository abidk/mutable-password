package abid.password.springmvc.model;

import abid.password.parameters.TimeParameter;

public class ExtendedPasswordFormBean {

  private String username;
  private String password;
  private TimeParameter timeParameter;

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

}

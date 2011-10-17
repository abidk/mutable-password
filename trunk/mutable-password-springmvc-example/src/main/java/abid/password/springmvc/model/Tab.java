package abid.password.springmvc.model;

public class Tab {

  private String name;
  private String urlName;
  private boolean active;

  public Tab(String name, String urlName, boolean active) {
    this.name = name;
    this.urlName = urlName;
    this.active = active;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrlName() {
    return urlName;
  }

  public void setUrlName(String urlName) {
    this.urlName = urlName;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
package abid.password.wicket.pages;

import org.apache.wicket.protocol.http.WebApplication;

public class LogoutPage extends BasePage {

  private static final long serialVersionUID = 1L;

  public LogoutPage() {
    getSession().invalidateNow();
    setResponsePage(WebApplication.get().getHomePage());
  }

}

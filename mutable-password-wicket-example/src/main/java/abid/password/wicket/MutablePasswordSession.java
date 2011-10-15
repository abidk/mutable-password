package abid.password.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import abid.password.wicket.model.User;
import abid.password.wicket.service.UserException;
import abid.password.wicket.service.UserService;

public class MutablePasswordSession extends AuthenticatedWebSession {

  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(MutablePasswordSession.class);

  public MutablePasswordSession(Request request) {
    super(request);
  }

  @Override
  public boolean authenticate(String username, String password) {
    boolean authenticated = false;
    UserService userService = MutablePasswordApplication.get().getUserService();
    try {
      User user = userService.authenticate(username, password);
      authenticated = user != null;
      signIn(authenticated);
    } catch (UserException e) {
      log.error("Error occurred whilst authenticating the user", e);
    }
    return authenticated;
  }

  @Override
  public Roles getRoles() {
    Roles roles = new Roles();
    if (isSignedIn()) {
      roles.add(Roles.ADMIN);
    }
    return roles;
  }

  public static MutablePasswordSession get() {
    return (MutablePasswordSession) Session.get();
  }
}

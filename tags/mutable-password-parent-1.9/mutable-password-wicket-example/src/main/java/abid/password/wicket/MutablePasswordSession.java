/**
 * Copyright 2012 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package abid.password.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class MutablePasswordSession extends AuthenticatedWebSession {

  private static final long serialVersionUID = 1L;

  public MutablePasswordSession(Request request) {
    super(request);
  }

  @Override
  public boolean authenticate(String username, String password) {
    boolean authenticated = true;
    //UserService userService = MutablePasswordApplication.get().getUserService();
    //try {
    //  User user = userService.authenticate(username, password);
    //  authenticated = user != null;
    signIn(authenticated);
    //} catch (UserException e) {
    //  log.error("Error occurred whilst authenticating the user", e);
    //}
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

/**
 * Copyright 2011 Abid Khalil
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

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import abid.password.parameters.TimeParameter;
import abid.password.types.ExtendedPassword;
import abid.password.types.SimplePassword;
import abid.password.wicket.pages.CreateUserPage;
import abid.password.wicket.pages.LoginPage;
import abid.password.wicket.pages.LogoutPage;
import abid.password.wicket.pages.UsersPage;
import abid.password.wicket.service.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.WorkManager;

@Singleton
public class MutablePasswordApplication extends AuthenticatedWebApplication {

  @Inject
  private WorkManager unitOfWork;
  @Inject
  private UserService userService;

  @Override
  protected void init() {
    super.init();
    mountPage("login", LoginPage.class);
    mountPage("logout", LogoutPage.class);
    mountPage("users", UsersPage.class);
    mountPage("createUser", CreateUserPage.class);
    createExampleUsers();
  }

  public Class<? extends WebPage> getHomePage() {
    return CreateUserPage.class;
  }

  protected Class<? extends WebPage> getSignInPageClass() {
    return LoginPage.class;
  }

  protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
    return MutablePasswordSession.class;
  }

  public static MutablePasswordApplication get() {
    return (MutablePasswordApplication) Application.get();
  }

  private void createExampleUsers() {
    unitOfWork.beginWork();
    try {
      //String user = "Example1";
      //String password = RomanNumeralPassword.createPassword("romannumeral", TimeParameter.MINUTE).getPassword();
      //userService.saveUser(user, password);
      //user = "Example2";
      //password = CaesarCipherPassword.createPassword("caesar", TimeParameter.MINUTE).getPassword();
      //userService.saveUser(user, password);
      String user = "Example3";
      String password = ExtendedPassword.createPassword("second_", TimeParameter.SECOND).getPassword();
      userService.saveUser(user, password);
      user = "Example4";
      password = ExtendedPassword.createPassword("minute_", TimeParameter.MINUTE).getPassword();
      userService.saveUser(user, password);
      user = "Example5";
      password = ExtendedPassword.createPassword("hourly_", TimeParameter.HOUR).getPassword();
      userService.saveUser(user, password);
      user = "Example6";
      password = ExtendedPassword.createPassword("day_of_month_", TimeParameter.DAY_OF_MONTH).getPassword();
      userService.saveUser(user, password);
      user = "admin";
      password = new SimplePassword("admin").getPassword();
      userService.saveUser(user, password);
    } finally {
      unitOfWork.endWork();
    }
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}

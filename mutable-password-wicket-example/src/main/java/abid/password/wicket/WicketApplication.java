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
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import abid.password.parameters.TimeParameter;
import abid.password.types.CaesarCipherPassword;
import abid.password.types.ExtendedPassword;
import abid.password.types.RomanNumeralPassword;
import abid.password.wicket.pages.CreateUserPage;
import abid.password.wicket.pages.LoginPage;
import abid.password.wicket.service.UserService;

import com.google.inject.Inject;
import com.wideplay.warp.persist.WorkManager;

public class WicketApplication extends WebApplication {

  @Inject
  private WorkManager unitOfWork;
  @Inject
  private UserService userService;

  @Override
  protected void init() {
    mountPage("login", LoginPage.class);
    mountPage("createUser", CreateUserPage.class);
    createExampleUsers();
  }

  public Class<? extends WebPage> getHomePage() {
    return LoginPage.class;
  }

  public static WicketApplication get() {
    return (WicketApplication) Application.get();
  }

  private void createExampleUsers() {
    unitOfWork.beginWork();
    try {
      String user = "Example1";
      String password = RomanNumeralPassword.createPassword("romannumeral", TimeParameter.MINUTE).getPassword();
      userService.saveUser(user, password);
      user = "Example2";
      password = CaesarCipherPassword.createPassword("caesar", TimeParameter.MINUTE).getPassword();
      userService.saveUser(user, password);
      user = "Example3";
      password = ExtendedPassword.createPassword("second", TimeParameter.SECOND).getPassword();
      userService.saveUser(user, password);
      user = "Example4";
      password = ExtendedPassword.createPassword("minute", TimeParameter.MINUTE).getPassword();
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

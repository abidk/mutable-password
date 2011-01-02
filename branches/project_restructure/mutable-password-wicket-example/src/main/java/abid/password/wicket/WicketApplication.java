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

import abid.password.wicket.dao.UserDao;
import abid.password.wicket.dao.hibernate.UserDaoImpl;
import abid.password.wicket.pages.ExamplePage;
import abid.password.wicket.service.UserService;
import abid.password.wicket.service.impl.UserServiceImpl;

public class WicketApplication extends WebApplication {

  private UserService userService;

  @Override
  protected void init() {
    // Should really use a dependency injector e.g. Spring, Guice
    // This will have suffice for example
    userService = new UserServiceImpl();
    UserDao userDao = new UserDaoImpl();
    ((UserServiceImpl) userService).setUserDao(userDao);
  }

  public Class<? extends WebPage> getHomePage() {
    return ExamplePage.class;
  }

  public static WicketApplication getApplication() {
    return (WicketApplication) Application.get();
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}

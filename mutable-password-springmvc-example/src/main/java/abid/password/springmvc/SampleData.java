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
package abid.password.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import abid.password.parameters.TimeParameter;
import abid.password.springmvc.service.UserService;
import abid.password.types.ExtendedPassword;
import abid.password.types.SimplePassword;

public class SampleData {

  private static final Logger log = LoggerFactory.getLogger(SampleData.class);

  @Autowired
  private UserService userService;

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /*
   * Initiate the database with users.
   */
  public void initDbWithUsers() {
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
    log.info("Added users!");
  }
}

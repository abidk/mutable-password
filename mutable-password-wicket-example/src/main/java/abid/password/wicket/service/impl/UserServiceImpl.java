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

package abid.password.wicket.service.impl;

import abid.password.Password;
import abid.password.types.PasswordFactory;
import abid.password.wicket.dao.UserDao;
import abid.password.wicket.model.User;
import abid.password.wicket.service.UserException;
import abid.password.wicket.service.UserService;

public class UserServiceImpl implements UserService {

  private UserDao userDao;

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public User authenticate(String username, String password) throws UserException {
    try {
      User user = userDao.getUser(username);
      if (user == null) {
        throw new UserException("User does not exist.");
      }

      Password userPass = PasswordFactory.getInstance(user.getPassword());
      boolean result = userPass.confirmPassword(password);
      if (!result) {
        throw new UserException("Password is wrong.");
      }
      return user;
    } catch (Exception e) {
      throw new UserException("An error occurred whilst authenticating.", e);
    }
  }
}

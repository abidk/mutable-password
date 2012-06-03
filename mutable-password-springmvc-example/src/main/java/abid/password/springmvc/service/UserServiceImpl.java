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
package abid.password.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abid.password.MutablePasswordParser;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.springmvc.dao.UserDao;
import abid.password.springmvc.model.User;

@Service
public class UserServiceImpl implements UserService {

  private UserDao userDao;

  @Autowired
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public User authenticate(String username, String password) throws UserException {
    User user = userDao.getUser(username);
    if (user == null) {
      throw new UserException("User does not exist.");
    }

    Password userPass = null;
    try {
      userPass = new MutablePasswordParser().parse(user.getPassword());
    } catch (Exception e) {
      throw new UserException("An error occurred whilst parsing the password.", e);
    }

    if (userPass == null) {
      throw new UserException("Password is wrong.");
    }

    boolean result;
    try {
      result = userPass.confirmPassword(password);
      if (!result) {
        throw new UserException("Password is wrong.");
      }
    } catch (PasswordException e) {
      throw new UserException("An error occurred whilst parsing the password.", e);
    }

    return user;
  }

  public void saveUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    userDao.saveUser(user);
  }

  public List<User> getUsers() {
    List<User> users = userDao.getUsers();
    return users;
  }

}

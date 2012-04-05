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
package abid.password.swing;

import java.util.Set;

import abid.password.MutablePassword;
import abid.password.MutablePasswordParser;
import abid.password.Password;
import abid.password.PasswordException;
import abid.password.StatefulMutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.swing.model.User;
import abid.password.types.CaesarCipherPasswordBuilder;
import abid.password.types.ExtendedPasswordBuilder;
import abid.password.types.ExtendedTimeLockPasswordBuilder;
import abid.password.types.RomanNumeralPasswordBuilder;
import abid.password.types.RotatingPasswordBuilder;
import abid.password.types.SimplePassword;
import abid.password.types.TimeLockPasswordBuilder;

public class FormController {

  private Application application;

  public FormController(Application application) {
    this.application = application;
  }

  public void loadCreateUserUI() {
    application.getForm().loadCreateUserForm();
  }

  public void loadLoginUI() {
    application.getForm().loadLoginForm();
  }

  public void refreshLoginUI() {
    application.getForm().refreshLoginForm();
  }

  public User loginUser(String username, char[] password) {
    User user = application.getUser(username);
    if (user == null) {
      return null;
    }

    Password userPass = null;
    try {
      userPass = new MutablePasswordParser().parse(user.getPassword());
    } catch (Exception e) {
      return null;
    }

    if (userPass == null) {
      return null;
    }

    // if it's a stateful password set the state
    if (userPass instanceof StatefulMutablePassword) {
      ((StatefulMutablePassword) userPass).setState(user.getState());
    }

    boolean result;
    try {
      result = userPass.confirmPassword(String.valueOf(password));
      if (!result) {
        return null;
      }
    } catch (PasswordException e) {
      return null;
    }

    return user;
  }

  public Set<User> getUsers() {
    Set<User> users = application.getUsers();
    return users;
  }

  public void saveExtendedUser(String username, String password, TimeParameter parameter) {
    MutablePassword mutablePassword = new ExtendedPasswordBuilder().createPassword(password, parameter);
    saveUser(username, mutablePassword.getPassword());
  }

  public void saveExtendedTimeLockUser(String username, String password, TimeParameter extendedParameter, TimeParameter extendedTimelock, int start, int end) {
    MutablePassword mutablePassword = new ExtendedTimeLockPasswordBuilder().createPassword(password, extendedParameter, extendedTimelock, start, end);
    saveUser(username, mutablePassword.getPassword());
  }

  public void saveCaesarCipherUser(String username, String password, TimeParameter parameter) {
    MutablePassword mutablePassword = new CaesarCipherPasswordBuilder().createPassword(password, parameter);
    saveUser(username, mutablePassword.getPassword());
  }

  public void saveTimeLockUser(String username, String password, TimeParameter parameter, int start, int end) {
    MutablePassword mutablePassword = new TimeLockPasswordBuilder().createPassword(password, parameter, start, end);
    saveUser(username, mutablePassword.getPassword());
  }

  public void saveSimpleUser(String username, String password) {
    SimplePassword simplePass = new SimplePassword(password);
    saveUser(username, simplePass.getPassword());
  }

  public void saveRomanNumeralUser(String username, String password, TimeParameter parameter) {
    MutablePassword mutablePassword = new RomanNumeralPasswordBuilder().createPassword(password, parameter);
    saveUser(username, mutablePassword.getPassword());
  }

  public void saveRotatingPasswordUser(String username, String password, String parameter) {
    MutablePassword mutablePassword = new RotatingPasswordBuilder().createPassword(password, parameter);
    saveUser(username, mutablePassword.getPassword());
  }

  private void saveUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    application.addUser(user);
  }

}

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
package abid.password.swing;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import abid.password.parameters.TimeParameter;
import abid.password.swing.model.User;
import abid.password.types.CaesarCipherPasswordBuilder;
import abid.password.types.ExtendedPasswordBuilder;
import abid.password.types.RomanNumeralPasswordBuilder;
import abid.password.types.SimplePassword;

public class Application {

  public static final String APPLICATION_TITLE = "Mutable Password Example";
  public static final int APPLICATION_WIDTH = 500;
  public static final int APPLICATION_HEIGHT = 400;

  private Set<User> users = new HashSet<User>();
  private FormUI form;
  private FormController controller;

  public Application() {
  }

  public void init() {
    // add some example data
    createExampleUsers();

    // init gui
    controller = new FormController(this);
    form = new FormUI(this);
    form.init();
    form.loadLoginForm();
  }

  private void createExampleUsers() {
    users.clear();
    User user3 = new User();
    user3.setUsername("Example1");
    String password3 = new RomanNumeralPasswordBuilder().createPassword("romannumeral", TimeParameter.MINUTE).getPassword();
    user3.setPassword(password3);
    users.add(user3);

    User user4 = new User();
    user4.setUsername("Example2");
    String password4 = new CaesarCipherPasswordBuilder().createPassword("caesar", TimeParameter.MINUTE).getPassword();
    user4.setPassword(password4);
    users.add(user4);

    User user1 = new User();
    user1.setUsername("Example3");
    String password1 = new ExtendedPasswordBuilder().createPassword("second", TimeParameter.SECOND).getPassword();
    user1.setPassword(password1);
    users.add(user1);

    User user2 = new User();
    user2.setUsername("Example4");
    String password2 = new ExtendedPasswordBuilder().createPassword("minute", TimeParameter.MINUTE).getPassword();
    user2.setPassword(password2);
    users.add(user2);

    User user5 = new User();
    user5.setUsername("admin");
    user5.setPassword(new SimplePassword("admin").getPassword());
    users.add(user5);
  }

  public FormUI getForm() {
    return form;
  }

  public FormController getController() {
    return controller;
  }

  public Set<User> getUsers() {
    return users;
  }

  public User getUser(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  public void addUser(User user) {
    users.remove(user);
    users.add(user);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame window = new JFrame();
        window.setTitle(APPLICATION_TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        window.setLocationRelativeTo(null);

        Application application = new Application();
        application.init();
        FormUI mainUI = application.getForm();
        window.setContentPane(mainUI);
        // window.setExtendedState(Frame.MAXIMIZED_BOTH);
        // window.pack();
        window.setVisible(true);
      }
    });
  }
}

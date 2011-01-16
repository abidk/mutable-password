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

package abid.password.swing.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abid.password.swing.Application;
import abid.password.swing.model.User;

import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

public class LoginForm extends AbstractForm {

  private JLabel feedbackLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private AbstractButton loginButton;
  private AbstractButton createUserButton;
  private JList userList;
  private AbstractButton refreshButton;

  private ActionListener actionListener = new ActionListener() {

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginButton) {
        if (validateComponents()) {
          String username = usernameField.getText();
          char[] password = passwordField.getPassword();
          User user = getApplication().getController().loginUser(username, password);
          if (user != null) {
            setInfo("User '" + user.getUsername() + "' logged in.");
          } else {
            setError("Failed to login.");
          }
        }
      } else if (e.getSource() == createUserButton) {
        getApplication().getController().loadCreateUserUI();
      } else if (e.getSource() == refreshButton) {
        getApplication().getController().refreshLoginUI();
      }
    }
  };

  public LoginForm(Application application) throws FormException {
    super(application, "Login.jfrm");
  }

  public void initComponents(FormPanel form) {
    feedbackLabel = form.getLabel("form.feedbackLabel");
    setFeedbackLabel(feedbackLabel);
    setInfo(null);
    usernameField = form.getTextField("form.usernameField");
    passwordField = (JPasswordField) form.getComponentByName("form.passwordField");
    loginButton = form.getButton("form.loginButton");
    loginButton.addActionListener(actionListener);
    createUserButton = form.getButton("form.createUserButton");
    createUserButton.addActionListener(actionListener);
    userList = form.getList("form.userList");
    userList.setCellRenderer(new UserListRenderer(getApplication()));
    refreshButton = form.getButton("form.refreshButton");
    refreshButton.addActionListener(actionListener);
  }

  public boolean validateComponents() {    
    setInfo(null);
    if (usernameField.getText().equals("")) {
      setError("Username field cannot be blank");
      return false;
    }
    if (passwordField.getPassword().equals("")) {
      setError("Password field cannot be blank");
      return false;
    }
    return true;
  }

  @Override
  public void refreshComponent() {
    Set<User> users = getApplication().getController().getUsers();
    DefaultListModel model = new DefaultListModel();
    for( User user : users ) {
      model.addElement(user);
    }
    userList.setModel(model);
  }
}

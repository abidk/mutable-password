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

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import abid.password.swing.Application;

import com.jeta.forms.components.panel.FormPanel;

public class SimplePasswordTab extends AbstractTab {

  private JLabel feedbackLabel;
  private JTextField usernameField;
  private JTextField passwordField;
  private AbstractButton saveButton;
  private AbstractButton cancelButton;

  private ActionListener actionListener = new ActionListener() {

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == saveButton) {
        if (validateComponents()) {
          String username = usernameField.getText();
          String password = passwordField.getText();

          getApplication().getController().saveSimpleUser(username, password);
          getApplication().getController().loadLoginUI();
        }
      } else if (e.getSource() == cancelButton) {
        getApplication().getController().loadLoginUI();
      }
    }
  };

  public SimplePasswordTab(Application application, FormPanel form) {
    super(application);
    initComponents(form);
  }

  public void initComponents(FormPanel form) {
    feedbackLabel = form.getLabel("simple.feedbackLabel");
    setFeedbackLabel(feedbackLabel);
    setInfo(null);
    usernameField = form.getTextField("simple.usernameField");
    passwordField = form.getTextField("simple.passwordField");
    saveButton = form.getButton("simple.saveButton");
    saveButton.addActionListener(actionListener);
    cancelButton = form.getButton("simple.cancelButton");
    cancelButton.addActionListener(actionListener);
  }

  public boolean validateComponents() {
    setInfo(null);
    if (usernameField.getText().equals("")) {
      setError("Username field cannot be blank");
      return false;
    }
    if (passwordField.getText().equals("")) {
      setError("Password field cannot be blank");
      return false;
    }
    return true;
  }

}

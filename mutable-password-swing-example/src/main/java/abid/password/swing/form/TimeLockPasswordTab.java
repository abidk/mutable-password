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
package abid.password.swing.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import abid.password.parameters.TimeParameter;
import abid.password.swing.Application;

import com.jeta.forms.components.panel.FormPanel;

public class TimeLockPasswordTab extends AbstractTab {

  private JLabel feedbackLabel;
  private JTextField usernameField;
  private JTextField passwordField;
  private JSpinner startField;
  private JSpinner endField;
  private JComboBox parameterField;
  private AbstractButton saveButton;
  private AbstractButton cancelButton;

  private ActionListener actionListener = new ActionListener() {

    public void actionPerformed(ActionEvent e) {
      if (validateComponents()) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        TimeParameter parameter = (TimeParameter) parameterField.getSelectedItem();
        int start = Integer.parseInt(startField.getValue().toString());
        int end = Integer.parseInt(endField.getValue().toString());
        getApplication().getController().saveTimeLockUser(username, password, parameter, start, end);
        getApplication().getController().loadLoginUI();
      } else if (e.getSource() == cancelButton) {
        getApplication().getController().loadLoginUI();
      }
    }
  };

  public TimeLockPasswordTab(Application application, FormPanel form) {
    super(application);
    initComponents(form);
  }

  public void initComponents(FormPanel form) {
    feedbackLabel = form.getLabel("timelock.feedbackLabel");
    setFeedbackLabel(feedbackLabel);
    setInfo(null);
    usernameField = form.getTextField("timelock.usernameField");
    passwordField = form.getTextField("timelock.passwordField");
    parameterField = form.getComboBox("timelock.parameterField");
    List<TimeParameter> choices = Arrays.asList(TimeParameter.HOUR, TimeParameter.MONTH, TimeParameter.DAY_OF_MONTH);
    for (TimeParameter param : choices) {
      parameterField.addItem(param);
    }
    startField = form.getSpinner("timelock.startField");
    endField = form.getSpinner("timelock.endField");
    saveButton = form.getButton("timelock.saveButton");
    saveButton.addActionListener(actionListener);
    cancelButton = form.getButton("timelock.cancelButton");
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

    try {
      Integer.parseInt(startField.getValue().toString());
    } catch (NumberFormatException e) {
      setError("Start field must be a number");
      return false;
    }

    try {
      Integer.parseInt(endField.getValue().toString());
    } catch (NumberFormatException e) {
      setError("End field must be a number");
      return false;
    }
    return true;
  }

}

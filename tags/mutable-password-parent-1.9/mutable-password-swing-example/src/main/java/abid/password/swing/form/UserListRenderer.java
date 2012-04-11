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

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abid.password.PasswordParseException;
import abid.password.MutablePasswordParser;
import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.StatefulMutablePassword;
import abid.password.evaluator.EvaluationException;
import abid.password.swing.Application;
import abid.password.swing.model.User;

import com.jeta.forms.components.panel.FormPanel;

public class UserListRenderer extends AbstractComponent implements ListCellRenderer {

  private JLabel usernameLabel;
  private JLabel passwordLabel;

  public UserListRenderer(Application application) {
    super(application, "UserListCell.jfrm");
    loadForm();
  }

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    User user = (User) value;
    usernameLabel.setText(user.getUsername());
    passwordLabel.setText(user.getPassword());
    try {
      Password password = new MutablePasswordParser().parse(user.getPassword());
      if (password instanceof StatefulMutablePassword) {
        StatefulMutablePassword statefulPassword = (StatefulMutablePassword) password;
        statefulPassword.setState(user.getState());
        passwordLabel.setText(statefulPassword.getEvaluatedPassword());
      } else if (password instanceof MutablePassword) {
        MutablePassword mutablePassword = (MutablePassword) password;
        passwordLabel.setText(mutablePassword.getEvaluatedPassword());
      }
    } catch (PasswordParseException e) {
      e.printStackTrace();
    } catch (EvaluationException e) {
      e.printStackTrace();
    }
    return getForm();
  }

  public void initComponents() {
    FormPanel form = getForm();
    usernameLabel = form.getLabel("list.username");
    passwordLabel = form.getLabel("list.password");
  }

  @Override
  public void refreshComponent() {

  }

}

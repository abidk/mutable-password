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

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.StatefulMutablePassword;
import abid.password.swing.Application;
import abid.password.swing.model.User;
import abid.password.types.PasswordFactory;

import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

public class UserListRenderer extends AbstractComponent implements ListCellRenderer {

  private JLabel usernameLabel;
  private JLabel passwordLabel;

  public UserListRenderer(Application application) {
    super(application, "UserListCell.jfrm");
    try {
      loadForm();
    } catch (FormException e) {
      e.printStackTrace();
    }
  }

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    User user = (User) value;
    usernameLabel.setText(user.getUsername());
    passwordLabel.setText(user.getPassword());
    try {
      Password password = PasswordFactory.getInstance(user.getPassword());
      if (password instanceof StatefulMutablePassword) {
        StatefulMutablePassword statefulPassword = (StatefulMutablePassword) password;
        statefulPassword.setState(user.getState());
        String evalatedPassword = statefulPassword.getEvaluatedPassword();
        passwordLabel.setText(evalatedPassword);
      } else if (password instanceof MutablePassword) {
        MutablePassword mutablePassword = (MutablePassword) password;
        String evalatedPassword = mutablePassword.getEvaluatedPassword();
        passwordLabel.setText(evalatedPassword);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    FormPanel form = getForm();
    return form;
  }

  public void initComponents(FormPanel form) {
    usernameLabel = form.getLabel("list.username");
    passwordLabel = form.getLabel("list.password");
  }

  @Override
  public void refreshComponent() {

  }

}

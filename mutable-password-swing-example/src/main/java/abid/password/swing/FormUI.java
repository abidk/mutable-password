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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.UIManager;

import abid.password.swing.form.CreateUserForm;
import abid.password.swing.form.LoginForm;

import com.jeta.forms.components.panel.FormPanel;

public class FormUI extends JPanel {

  private static final long serialVersionUID = 1L;

  private Application application;
  private LoginForm loginForm;
  private CreateUserForm createUserForm;

  public FormUI(Application application) {
    this.application = application;
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.setLayout(new BorderLayout());
  }

  public void init() {
    loginForm = new LoginForm(application);
    createUserForm = new CreateUserForm(application);
  }

  public void loadLoginForm() {
    loginForm.refreshComponent();
    FormPanel form = loginForm.getForm();
    loadForm(form);
  }

  public void loadCreateUserForm() {
    createUserForm.refreshComponent();
    FormPanel form = createUserForm.getForm();
    loadForm(form);
  }

  public void refreshLoginForm() {
    loginForm.refreshComponent();
  }

  private void loadForm(FormPanel form) {
    removeAll();
    add(form);
    form.setVisible(false);
    form.setVisible(true);
  }

}

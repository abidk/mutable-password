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

import java.io.IOException;
import java.io.InputStream;

import abid.password.swing.Application;

import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

public abstract class AbstractComponent {

  public final String FORM_DIR = "/";

  protected Application application;
  private String formName;
  private FormPanel form;

  public AbstractComponent(Application application, String formName) {
    this.application = application;
    this.formName = formName;
  }

  public void loadForm() throws FormException {
    InputStream inputStream = null;
    try {
      inputStream = getClass().getResourceAsStream(FORM_DIR + formName);
      form = new FormPanel(inputStream);
      initComponents(form);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public Application getApplication() {
    return application;
  }

  public FormPanel getForm() {
    return form;
  }

  public abstract void initComponents(FormPanel form);
  public abstract void refreshComponent();

}

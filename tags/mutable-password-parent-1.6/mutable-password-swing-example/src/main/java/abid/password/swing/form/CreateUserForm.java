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

import abid.password.swing.Application;

import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

public class CreateUserForm extends AbstractForm {

  private AbstractTab simplePasswordTab;
  private AbstractTab extendedPasswordTab;
  private AbstractTab extendedTimeLockPasswordTab;
  private AbstractTab shiftPasswordTab;
  private AbstractTab lockPasswordTab;

  public CreateUserForm(Application application) throws FormException {
    super(application, "CreateUser.jfrm");
    this.application = application;
  }

  @Override
  public void initComponents(FormPanel form) {
    simplePasswordTab = new SimplePasswordTab(application, form);
    extendedPasswordTab = new ExtendedPasswordTab(application, form);
    extendedTimeLockPasswordTab = new ExtendedTimeLockPasswordTab(application, form);
    shiftPasswordTab = new ShiftPasswordTab(application, form);
    lockPasswordTab = new TimeLockPasswordTab(application, form);
  }

  public AbstractTab getSimplePasswordTab() {
    return simplePasswordTab;
  }

  public AbstractTab getExtendedPasswordTab() {
    return extendedPasswordTab;
  }

  public AbstractTab getExtendedTimeLockPasswordTab() {
    return extendedTimeLockPasswordTab;
  }

  public AbstractTab getLockPasswordTab() {
    return lockPasswordTab;
  }

  public AbstractTab getShiftPasswordTab() {
    return shiftPasswordTab;
  }

  @Override
  public void refreshComponent() {    
  }

}

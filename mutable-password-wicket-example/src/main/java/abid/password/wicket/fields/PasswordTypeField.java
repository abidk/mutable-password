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
package abid.password.wicket.fields;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;

import abid.password.wicket.fields.PasswordTypeChoice.PasswordType;

public class PasswordTypeField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;
  private FormComponent<String> selectedForm;

  public PasswordTypeField(String id, IModel<String> model, IModel<PasswordType> selectedChoiceModel) {
    super(id, model);

    PasswordType selectedChoice = selectedChoiceModel.getObject();
    switch (selectedChoice) {
    case EXTENDED:
      selectedForm = new ExtendedPasswordField("group");
      break;
    case SHIFT:
      selectedForm = new CaesarCipherPasswordField("group");
      break;
    case TIME_LOCK:
      selectedForm = new TimeLockPasswordField("group");
      break;
    case EXTENDED_TIME_LOCK:
      selectedForm = new ExtendedTimeLockPasswordField("group");
      break;
    default:
      selectedForm = new SimplePasswordField("group");
      break;
    }

    add(selectedForm);
  }

  @Override
  protected void convertInput() {
    setConvertedInput(selectedForm.getConvertedInput());
  }

}

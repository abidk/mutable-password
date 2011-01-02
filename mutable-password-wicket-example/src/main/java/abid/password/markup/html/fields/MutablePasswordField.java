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

package abid.password.markup.html.fields;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.Model;

public class MutablePasswordField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;
  private FormComponent<String> selectedForm;

  public MutablePasswordField(String id) {
    this(id, null);
  }

  public MutablePasswordField(String id, String currentSelection) {
    super(id, new Model<String>(""));

    if (currentSelection == null) {
      currentSelection = "Simple";
    }

    if (currentSelection.equals("Extended")) {
      selectedForm = new ExtendedPasswordField("group");
    } else if (currentSelection.equals("Shift")) {
      selectedForm = new ShiftPasswordField("group");
    } else if (currentSelection.equals("Time Lock")) {
      selectedForm = new TimeLockPasswordField("group");
    } else if (currentSelection.equals("Extended Time Lock")) {
      selectedForm = new ExtendedTimeLockPasswordField("group");
    } else {
      selectedForm = new SimplePasswordField("group");
    }
    add(selectedForm);
  }

  @Override
  protected void convertInput() {
    setConvertedInput(selectedForm.getConvertedInput());
  }

}

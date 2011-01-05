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

package abid.password.wicket.fields;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import abid.password.MutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.types.TimeLockPassword;

public class TimeLockPasswordField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;

  private final FormComponent<String> passwordField;
  private final FormComponent<TimeParameter> timeChoice;
  private final FormComponent<Integer> startField;
  private final FormComponent<Integer> endField;

  public TimeLockPasswordField(String id) {
    this(id, new Model<String>(""));
  }

  public TimeLockPasswordField(String id, IModel<String> model) {
    super(id, model);
    passwordField = new TextField<String>("password", new Model<String>("")) {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean isRequired() {
        return true;
      }
    };

    List<TimeParameter> passwordChoices = Arrays.asList(TimeParameter.HOUR, TimeParameter.MONTH, TimeParameter.DAY_OF_MONTH);
    timeChoice = new DropDownChoice<TimeParameter>("parameter", new Model<TimeParameter>(), passwordChoices) {

      private static final long serialVersionUID = 1L;

      public boolean isRequired() {
        return true;
      };
    };

    startField = new TextField<Integer>("startField", new Model<Integer>(0)) {

      private static final long serialVersionUID = 1L;

      public boolean isRequired() {
        return true;
      };
    };
    startField.setType(Integer.class);

    endField = new TextField<Integer>("endField", new Model<Integer>(0)) {

      private static final long serialVersionUID = 1L;

      public boolean isRequired() {
        return true;
      };
    };
    endField.setType(Integer.class);

    add(passwordField);
    add(timeChoice);
    add(startField);
    add(endField);
  }

  @Override
  protected void convertInput() {
    String password = passwordField.getConvertedInput();
    TimeParameter parameter = timeChoice.getConvertedInput();
    Integer start = startField.getConvertedInput();
    Integer end = endField.getConvertedInput();

    MutablePassword extendedPassword = TimeLockPassword.createPassword(password, parameter, start, end);
    setConvertedInput(extendedPassword.getPassword());
  }

}

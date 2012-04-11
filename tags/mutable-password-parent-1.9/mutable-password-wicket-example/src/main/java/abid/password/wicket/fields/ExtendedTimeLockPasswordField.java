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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import abid.password.MutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.types.ExtendedTimeLockPasswordBuilder;

public class ExtendedTimeLockPasswordField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;

  private final FormComponent<String> passwordField;
  private final FormComponent<TimeParameter> extendedParameter;
  private final FormComponent<TimeParameter> timeChoice;
  private final FormComponent<Integer> startField;
  private final FormComponent<Integer> endField;

  public ExtendedTimeLockPasswordField(String id) {
    this(id, new Model<String>(""));
  }

  public ExtendedTimeLockPasswordField(String id, IModel<String> model) {
    super(id, model);
    passwordField = new TextField<String>("password", new Model<String>(""));
    passwordField.setRequired(true);
    add(passwordField);

    extendedParameter = new TimeParameterChoice("extendedParameter", new Model<TimeParameter>(), null);
    extendedParameter.setRequired(true);
    add(extendedParameter);

    List<TimeParameter> timeChoices = Arrays.asList(TimeParameter.HOUR, TimeParameter.MONTH, TimeParameter.DAY_OF_MONTH);
    timeChoice = new TimeParameterChoice("timeChoice", new Model<TimeParameter>(), timeChoices);
    timeChoice.setRequired(true);
    add(timeChoice);

    startField = new TextField<Integer>("startField", new Model<Integer>(0));
    startField.setRequired(true);
    startField.setType(Integer.class);
    add(startField);

    endField = new TextField<Integer>("endField", new Model<Integer>(0));
    endField.setRequired(true);
    endField.setType(Integer.class);
    add(endField);
  }

  @Override
  protected void convertInput() {
    String password = passwordField.getConvertedInput();
    TimeParameter extParameter = extendedParameter.getConvertedInput();
    TimeParameter timeParameter = timeChoice.getConvertedInput();
    Integer start = startField.getConvertedInput();
    Integer end = endField.getConvertedInput();

    MutablePassword extendedPassword = new ExtendedTimeLockPasswordBuilder().createPassword(password, extParameter, timeParameter, start, end);
    setConvertedInput(extendedPassword.getPassword());
  }

}

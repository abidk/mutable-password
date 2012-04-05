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
import abid.password.types.CaesarCipherPasswordBuilder;

public class CaesarCipherPasswordField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;

  private final FormComponent<String> passwordField;
  private final DropDownChoice<TimeParameter> timeChoice;

  public CaesarCipherPasswordField(String id) {
    this(id, new Model<String>(""));
  }

  public CaesarCipherPasswordField(String id, IModel<String> model) {
    super(id, model);
    passwordField = new TextField<String>("password", new Model<String>(""));
    passwordField.setRequired(true);
    add(passwordField);

    List<TimeParameter> choices = Arrays.asList(TimeParameter.HOUR, TimeParameter.MONTH, TimeParameter.DAY_OF_MONTH);
    timeChoice = new TimeParameterChoice("parameter", new Model<TimeParameter>(), choices);
    timeChoice.setRequired(true);
    add(timeChoice);
  }

  @Override
  protected void convertInput() {
    String password = passwordField.getConvertedInput();
    TimeParameter parameter = timeChoice.getConvertedInput();
    MutablePassword extendedPassword = new CaesarCipherPasswordBuilder().createPassword(password, parameter);
    setConvertedInput(extendedPassword.getPassword());
  }

}

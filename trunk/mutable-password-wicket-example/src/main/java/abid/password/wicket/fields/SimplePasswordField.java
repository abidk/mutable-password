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

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import abid.password.types.SimplePassword;

public class SimplePasswordField extends FormComponentPanel<String> {

  private static final long serialVersionUID = 1L;

  private final FormComponent<String> passwordField;

  public SimplePasswordField(String id) {
    this(id, new Model<String>(""));
  }

  public SimplePasswordField(String id, IModel<String> model) {
    super(id, model);
    passwordField = new PasswordTextField("password", new Model<String>(""));
    passwordField.setRequired(true);
    add(passwordField);
  }

  @Override
  protected void convertInput() {
    String password = passwordField.getConvertedInput();
    SimplePassword simplePass = new SimplePassword(password);
    setConvertedInput(simplePass.getPassword());
  }

}

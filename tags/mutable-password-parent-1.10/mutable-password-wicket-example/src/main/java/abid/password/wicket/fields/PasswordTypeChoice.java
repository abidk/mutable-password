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

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.model.IModel;


public class PasswordTypeChoice extends DropDownChoice<PasswordTypeChoice.PasswordType> {

  private static final long serialVersionUID = 1L;

  public enum PasswordType {
    SIMPLE, EXTENDED, SHIFT, TIME_LOCK, EXTENDED_TIME_LOCK;
  }

  public PasswordTypeChoice(final String id, final IModel<PasswordType> model) {
    super(id);
    setModel(model);
    setChoices(Arrays.asList(PasswordType.values()));
    setChoiceRenderer(new EnumChoiceRenderer<PasswordType>(this));
  }
}

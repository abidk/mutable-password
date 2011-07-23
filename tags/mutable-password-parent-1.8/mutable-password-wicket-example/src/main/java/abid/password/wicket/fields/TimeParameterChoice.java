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
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.model.IModel;

import abid.password.parameters.TimeParameter;

public class TimeParameterChoice extends DropDownChoice<TimeParameter> {

  private static final long serialVersionUID = 1L;

  public TimeParameterChoice(final String id, final IModel<TimeParameter> model, List<TimeParameter> list) {
    super(id);
    setModel(model);
    if (list == null) {
      list = Arrays.asList(TimeParameter.values());
    }
    setChoices(list);
    setChoiceRenderer(new EnumChoiceRenderer<TimeParameter>(this));
  }
}

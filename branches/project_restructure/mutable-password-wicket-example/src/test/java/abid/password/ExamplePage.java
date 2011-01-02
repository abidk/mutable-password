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

package abid.password;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import abid.password.markup.html.fields.ExtendedPasswordField;
import abid.password.markup.html.fields.ExtendedTimeLockPasswordField;
import abid.password.markup.html.fields.MutablePasswordField;
import abid.password.markup.html.fields.ShiftPasswordField;
import abid.password.markup.html.fields.SimplePasswordField;
import abid.password.markup.html.fields.TimeLockPasswordField;

public class ExamplePage extends WebPage {

  private final String CURRENT_SELECTION = "CURRENT_SELECTION";
  private String currentSelection;

  public ExamplePage(PageParameters pageParameter) {
    currentSelection = pageParameter.getString(CURRENT_SELECTION);

    FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");

    MutablePasswordForm mutablePasswordForm = new MutablePasswordForm("mutablePasswordForm");

    SimplePasswordForm simplePasswordForm = new SimplePasswordForm("SimplePasswordForm");
    ExtendedPasswordForm extendedPasswordForm = new ExtendedPasswordForm("extendedPasswordForm");
    ShiftPasswordForm shiftPasswordForm = new ShiftPasswordForm("shiftPasswordForm");
    TimeLockPasswordForm timeLockPasswordForm = new TimeLockPasswordForm("timeLockPasswordForm");
    ExtendedTimeLockPasswordForm extendedTimeLockPasswordForm = new ExtendedTimeLockPasswordForm("extendedTimeLockPasswordForm");

    add(feedbackPanel);
    add(mutablePasswordForm);
    add(simplePasswordForm);
    add(shiftPasswordForm);
    add(extendedPasswordForm);
    add(timeLockPasswordForm);
    add(extendedTimeLockPasswordForm);
  }

  public class MutablePasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private MutablePasswordField mutablePasswordField;

    public MutablePasswordForm(String id) {
      super(id);
      List<String> passwordChoices = Arrays.asList(new String[] { "Simple", "Shift", "Extended", "Time Lock", "Extended Time Lock" });
      DropDownChoice<String> passwordChoice = new DropDownChoice<String>("passwordType", new Model<String>(currentSelection), passwordChoices) {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean wantOnSelectionChangedNotifications() {
          return true;
        }

        @Override
        protected void onSelectionChanged(String newSelection) {
          PageParameters parameters = new PageParameters();
          parameters.add(CURRENT_SELECTION, newSelection);
          setResponsePage(new ExamplePage(parameters));
        }
      };

      mutablePasswordField = new MutablePasswordField("mutablePasswordField", currentSelection);

      add(passwordChoice);
      add(mutablePasswordField);
    }

    @Override
    protected void onSubmit() {
      System.out.println(mutablePasswordField.getConvertedInput());
    }
  }

  public class SimplePasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private SimplePasswordField passwordField;

    public SimplePasswordForm(String id) {
      super(id);
      passwordField = new SimplePasswordField("simplePasswordField");
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      System.out.println(passwordField.getConvertedInput());
    }
  }

  public class ExtendedPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private ExtendedPasswordField passwordField;

    public ExtendedPasswordForm(String id) {
      super(id);
      passwordField = new ExtendedPasswordField("extendedPasswordField");
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      System.out.println(passwordField.getConvertedInput());
    }
  }

  public class ShiftPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private ShiftPasswordField passwordField;

    public ShiftPasswordForm(String id) {
      super(id);
      passwordField = new ShiftPasswordField("shiftPasswordField");
      add(passwordField);

    }

    @Override
    protected void onSubmit() {
      System.out.println(passwordField.getConvertedInput());
    }
  }

  public class TimeLockPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private TimeLockPasswordField passwordField;

    public TimeLockPasswordForm(String id) {
      super(id);
      passwordField = new TimeLockPasswordField("timeLockPasswordField");
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      System.out.println(passwordField.getConvertedInput());
    }
  }

  public class ExtendedTimeLockPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private ExtendedTimeLockPasswordField passwordField;

    public ExtendedTimeLockPasswordForm(String id) {
      super(id);
      passwordField = new ExtendedTimeLockPasswordField("extendedTimeLockPasswordField");
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      System.out.println(passwordField.getConvertedInput());
    }
  }
}

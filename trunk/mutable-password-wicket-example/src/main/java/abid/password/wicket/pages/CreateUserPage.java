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

package abid.password.wicket.pages;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import abid.password.wicket.fields.ExtendedPasswordField;
import abid.password.wicket.fields.ExtendedTimeLockPasswordField;
import abid.password.wicket.fields.MutablePasswordField;
import abid.password.wicket.fields.ShiftPasswordField;
import abid.password.wicket.fields.SimplePasswordField;
import abid.password.wicket.fields.TimeLockPasswordField;
import abid.password.wicket.service.UserService;

import com.google.inject.Inject;

public class CreateUserPage extends BasePage {

  @Inject
  private UserService userService;

  private final String CURRENT_SELECTION = "CURRENT_SELECTION";
  private String currentSelection = "Simple";

  public CreateUserPage(PageParameters pageParameter) {
    if (pageParameter.getString(CURRENT_SELECTION) != null) {
      currentSelection = pageParameter.getString(CURRENT_SELECTION);
    }

    MutablePasswordForm mutablePasswordForm = new MutablePasswordForm("mutablePasswordForm");
    SimplePasswordForm simplePasswordForm = new SimplePasswordForm("SimplePasswordForm");
    ExtendedPasswordForm extendedPasswordForm = new ExtendedPasswordForm("extendedPasswordForm");
    ShiftPasswordForm shiftPasswordForm = new ShiftPasswordForm("shiftPasswordForm");
    TimeLockPasswordForm timeLockPasswordForm = new TimeLockPasswordForm("timeLockPasswordForm");
    ExtendedTimeLockPasswordForm extendedTimeLockPasswordForm = new ExtendedTimeLockPasswordForm("extendedTimeLockPasswordForm");

    add(mutablePasswordForm);
    add(simplePasswordForm);
    add(shiftPasswordForm);
    add(extendedPasswordForm);
    add(timeLockPasswordForm);
    add(extendedTimeLockPasswordForm);
  }

  public class MutablePasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

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
          setResponsePage(new CreateUserPage(parameters));
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      MutablePasswordField passwordField = new MutablePasswordField("mutablePasswordField", passwordModel, currentSelection);
      passwordField.setRequired(true);

      add(userField);
      add(passwordChoice);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public class SimplePasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public SimplePasswordForm(String id) {
      super(id);
      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      SimplePasswordField passwordField = new SimplePasswordField("simplePasswordField", passwordModel);
      passwordField.setRequired(true);

      add(userField);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public class ExtendedPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public ExtendedPasswordForm(String id) {
      super(id);
      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      ExtendedPasswordField passwordField = new ExtendedPasswordField("extendedPasswordField", passwordModel);
      passwordField.setRequired(true);

      add(userField);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public class ShiftPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public ShiftPasswordForm(String id) {
      super(id);
      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      ShiftPasswordField passwordField = new ShiftPasswordField("shiftPasswordField", passwordModel);
      passwordField.setRequired(true);
      add(userField);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public class TimeLockPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public TimeLockPasswordForm(String id) {
      super(id);
      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      TimeLockPasswordField passwordField = new TimeLockPasswordField("timeLockPasswordField", passwordModel);
      passwordField.setRequired(true);
      add(userField);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public class ExtendedTimeLockPasswordForm extends Form<Void> {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public ExtendedTimeLockPasswordForm(String id) {
      super(id);
      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      ExtendedTimeLockPasswordField passwordField = new ExtendedTimeLockPasswordField("extendedTimeLockPasswordField", passwordModel);
      passwordField.setRequired(true);
      add(userField);
      add(passwordField);
    }

    @Override
    protected void onSubmit() {
      userService.saveUser(username, password);
      info("Saved user!");
    }
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}

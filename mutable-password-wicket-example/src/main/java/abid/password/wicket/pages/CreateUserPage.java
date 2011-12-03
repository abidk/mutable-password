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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import abid.password.wicket.fields.CaesarCipherPasswordField;
import abid.password.wicket.fields.ExtendedPasswordField;
import abid.password.wicket.fields.ExtendedTimeLockPasswordField;
import abid.password.wicket.fields.PasswordTypeField;
import abid.password.wicket.fields.PasswordTypeChoice;
import abid.password.wicket.fields.PasswordTypeChoice.PasswordType;
import abid.password.wicket.fields.SimplePasswordField;
import abid.password.wicket.fields.TimeLockPasswordField;
import abid.password.wicket.service.UserService;

import com.google.inject.Inject;

@AuthorizeInstantiation(Roles.ADMIN)
public class CreateUserPage extends BasePage {

  private static final String PASSWORD_TYPE_SELECTION = "PASSWORD_TYPE_SELECTION";
  private static final long serialVersionUID = 1L;

  @Inject
  private UserService userService;
  private PasswordType passwordTypeSelection = PasswordType.SIMPLE;

  public CreateUserPage(PageParameters pageParameter) {
    if (!pageParameter.get(PASSWORD_TYPE_SELECTION).isEmpty()) {
      String selection = pageParameter.get(PASSWORD_TYPE_SELECTION).toString();
      passwordTypeSelection = PasswordType.valueOf(selection);
    }

    List<ITab> tabs = new ArrayList<ITab>();
    tabs.add(new AbstractTab(new Model<String>("All Passwords")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new AllPasswordPanel(panelId);
      }
    });
    tabs.add(new AbstractTab(new Model<String>("Simple")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new SimplePasswordPanel(panelId);
      }
    });
    tabs.add(new AbstractTab(new Model<String>("Extended")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new ExtendedPasswordPanel(panelId);
      }
    });
    tabs.add(new AbstractTab(new Model<String>("Caesar Cipher")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new CaesarCipherPasswordPanel(panelId);
      }
    });
    tabs.add(new AbstractTab(new Model<String>("Time Lock")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new TimeLockPasswordPanel(panelId);
      }
    });
    tabs.add(new AbstractTab(new Model<String>("Extended Time Lock")) {

      private static final long serialVersionUID = 1L;

      public Panel getPanel(String panelId) {
        return new ExtendedTimeLockPasswordPanel(panelId);
      }
    });

    AjaxTabbedPanel tabsPanel = new AjaxTabbedPanel("tabsPanel", tabs);
    add(tabsPanel);

  }

  public class AllPasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public AllPasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {
        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      IModel<PasswordType> passwordTypeModel = new Model<PasswordType>(passwordTypeSelection);
      PasswordTypeChoice passwordChoice = new PasswordTypeChoice("passwordTypes", passwordTypeModel) {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean wantOnSelectionChangedNotifications() {
          // relies on AJAX, could instead add an update button TODO?
          return true;
        }

        @Override
        protected void onSelectionChanged(PasswordType passwordType) {
          PageParameters parameters = new PageParameters();
          parameters.add(PASSWORD_TYPE_SELECTION, passwordType.name());
          setResponsePage(new CreateUserPage(parameters));
        }
      };
      form.add(passwordChoice);

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      PasswordTypeField passwordField = new PasswordTypeField("passwordField", passwordModel, passwordTypeModel);
      passwordField.setRequired(true);
      form.add(passwordField);

      add(form);
    }
  }

  public class SimplePasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public SimplePasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {

        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      SimplePasswordField passwordField = new SimplePasswordField("simplePasswordField", passwordModel);
      passwordField.setRequired(true);
      form.add(passwordField);
      add(form);
    }
  }

  public class ExtendedPasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public ExtendedPasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {

        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      ExtendedPasswordField passwordField = new ExtendedPasswordField("extendedPasswordField", passwordModel);
      passwordField.setRequired(true);
      form.add(passwordField);

      add(form);
    }
  }

  public class CaesarCipherPasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public CaesarCipherPasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {
        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      CaesarCipherPasswordField passwordField = new CaesarCipherPasswordField("caesarCipherField", passwordModel);
      passwordField.setRequired(true);
      form.add(passwordField);
      add(form);
    }

  }

  public class TimeLockPasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public TimeLockPasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {
        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      TimeLockPasswordField passwordField = new TimeLockPasswordField("timeLockPasswordField", passwordModel);
      passwordField.setRequired(true);
      form.add(passwordField);

      add(form);
    }

  }

  public class ExtendedTimeLockPasswordPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public ExtendedTimeLockPasswordPanel(String id) {
      super(id);

      Form<Void> form = new Form<Void>("form") {
        private static final long serialVersionUID = 1L;

        @Override
        protected void onSubmit() {
          userService.saveUser(username, password);
          setResponsePage(UsersPage.class);
        }
      };

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> userField = new TextField<String>("userField", usernameModel);
      userField.setRequired(true);
      form.add(userField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      ExtendedTimeLockPasswordField passwordField = new ExtendedTimeLockPasswordField("extendedTimeLockPasswordField", passwordModel);
      passwordField.setRequired(true);
      form.add(passwordField);

      add(form);
    }
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}

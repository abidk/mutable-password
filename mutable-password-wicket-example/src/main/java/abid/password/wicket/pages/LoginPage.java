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

import java.util.List;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import abid.password.MutablePassword;
import abid.password.Password;
import abid.password.types.PasswordFactory;
import abid.password.wicket.MutablePasswordApplication;
import abid.password.wicket.MutablePasswordSession;
import abid.password.wicket.components.AjaxFallbackLabel;
import abid.password.wicket.model.User;
import abid.password.wicket.service.UserException;
import abid.password.wicket.service.UserService;

import com.google.inject.Inject;

public class LoginPage extends BasePage {

  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

  @Inject
  private UserService userService;

  public LoginPage() {
    LoginPanel loginPanel = new LoginPanel("loginPanel");
    add(loginPanel);

    LoadableDetachableModel<List<User>> usersModel = new LoadableDetachableModel<List<User>>() {

      private static final long serialVersionUID = 1L;

      @Override
      protected List<User> load() {
        return userService.getUsers();
      }
    };

    PageableListView<User> dataList = new PageableListView<User>("usersList", usersModel, 100) {

      private static final long serialVersionUID = 1L;

      @Override
      protected void populateItem(ListItem<User> item) {
        final User data = item.getModelObject();
        Label userLabel = new Label("userLabel", data.getUsername());
        Label passLabel = new Label("passLabel", data.getPassword());

        try {
          Password password = PasswordFactory.getInstance(data.getPassword());
          if (password instanceof MutablePassword) {
            MutablePassword mutablePassword = (MutablePassword) password;
            String evalatedPassword = mutablePassword.getEvaluatedPassword();
            passLabel = new Label("passLabel", evalatedPassword);
          }
        } catch (Exception e) {
          log.error("Could not create the mutable password", e);
        }

        item.add(userLabel);
        item.add(passLabel);
      }
    };

    int refreshTime = 3;
    final WebMarkupContainer usersContainer = new WebMarkupContainer("usersContainer");
    usersContainer.setOutputMarkupId(true);
    usersContainer.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(refreshTime)));
    usersContainer.add(dataList);

    String refreshInformation = String.format("Password refreshes every %s seconds.", refreshTime);
    String javascriptDisabledMsg = "Javascript is disabled you will need to refresh the page manually.";
    AjaxFallbackLabel refreshLabel = new AjaxFallbackLabel("refreshInformation", refreshInformation, javascriptDisabledMsg);

    add(usersContainer);
    add(refreshLabel);
  }

  public class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public LoginPanel(final String id) {
      super(id);

      Form<Void> loginForm = new Form<Void>("loginForm") {

        private static final long serialVersionUID = 1L;

        @Override
        public final void onSubmit() {
          UserService userService = MutablePasswordApplication.get().getUserService();
          try {
            userService.authenticate(username, password);
            MutablePasswordSession session = MutablePasswordSession.get();
            if (session.signIn(username, password)) {
              setResponsePage(WebApplication.get().getHomePage());
            }
          } catch (UserException e) {
            error( "Please enter the correct username/password from the users table below. Your password may have changed whilst you were typing it!");
          }
          

        }
      };
      add(loginForm);

      PropertyModel<String> usernameModel = new PropertyModel<String>(this, "username");
      TextField<String> usernameField = new TextField<String>("usernameField", usernameModel);
      usernameField.setRequired(true);
      loginForm.add(usernameField);

      PropertyModel<String> passwordModel = new PropertyModel<String>(this, "password");
      PasswordTextField passwordField = new PasswordTextField("passwordField", passwordModel);
      passwordField.setRequired(true);
      loginForm.add(passwordField);
    }
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}

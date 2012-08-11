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
package abid.password.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import abid.password.MutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.springmvc.FeedbackMessage;
import abid.password.springmvc.model.CaesarCipherPasswordForm;
import abid.password.springmvc.model.ExtendedPasswordForm;
import abid.password.springmvc.model.ExtendedTimeLockPasswordForm;
import abid.password.springmvc.model.SimplePasswordForm;
import abid.password.springmvc.model.Tab;
import abid.password.springmvc.model.TabPanel;
import abid.password.springmvc.model.TimeLockPasswordForm;
import abid.password.springmvc.service.UserService;
import abid.password.springmvc.validator.MutablePasswordValidator;
import abid.password.types.CaesarCipherPasswordBuilder;
import abid.password.types.ExtendedPasswordBuilder;
import abid.password.types.ExtendedTimeLockPasswordBuilder;
import abid.password.types.TimeLockPasswordBuilder;

@Controller
@RequestMapping("/create")
public class CreateUserController {

  public static final String TAB_SIMPLE_PASWORD = "simple";
  public static final String TAB_EXTENDED_PASWORD = "extended";
  public static final String TAB_CAESAR_PASWORD = "caesarcipher";
  public static final String TAB_EXTENDED_TIMELOCK_PASWORD = "extendedtimelock";
  public static final String TAB_TIMELOCK_PASWORD = "timelock";
  public static final String DEFAULT_SELECTED_TAB = TAB_SIMPLE_PASWORD;

  private UserService userService;
  private MutablePasswordValidator mutablePasswordValidator;
  private FeedbackMessage feedbackMessage;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setMutablePasswordValidator(MutablePasswordValidator mutablePasswordValidator) {
    this.mutablePasswordValidator = mutablePasswordValidator;
  }

  @Autowired
  public void setFeedbackMessage(FeedbackMessage feedbackMessage) {
    this.feedbackMessage = feedbackMessage;
  }

  @ModelAttribute("simplePasswordForm")
  public SimplePasswordForm getSimplePasswordForm() {
    return new SimplePasswordForm();
  }

  @ModelAttribute("extendedPasswordForm")
  public ExtendedPasswordForm getExtendedPasswordForm() {
    return new ExtendedPasswordForm();
  }

  @ModelAttribute("extendedTimeLockPasswordForm")
  public ExtendedTimeLockPasswordForm getExtendedTimeLockPasswordForm() {
    return new ExtendedTimeLockPasswordForm();
  }

  @ModelAttribute("timeLockPasswordForm")
  public TimeLockPasswordForm getTimeLockPasswordForm() {
    return new TimeLockPasswordForm();
  }

  @ModelAttribute("caesarCipherPasswordForm")
  public CaesarCipherPasswordForm getCaesarCipherPasswordForm() {
    return new CaesarCipherPasswordForm();
  }

  @RequestMapping(method = RequestMethod.GET, value = { "", "/" })
  public ModelAndView handleGetCreateUser() {
    return new ModelAndView("redirect:/app/create/" + DEFAULT_SELECTED_TAB);
  }

  @RequestMapping(method = RequestMethod.GET, value = { "/{tab}" })
  public ModelAndView handleGetCreateUserTab(@PathVariable("tab") String tab) {
    TabPanel tabPanel = buildTabPanel(tab);
    if (!tabPanel.isValidTab(tab)) {
      return new ModelAndView("redirect:/app/create/");
    }

    ModelAndView model = new ModelAndView("createUser");
    model.getModelMap().put("tabPanel", tabPanel);
    model.getModelMap().put("timeParameter", TimeParameter.values());
    return model;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createSimplePassword")
  public ModelAndView handlePostSimplePassword(@ModelAttribute("formBean") SimplePasswordForm formBean, BindingResult result) {

    mutablePasswordValidator.validateSimplePassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_SIMPLE_PASWORD);
    }

    String username = formBean.getUsername();
    String password = formBean.getPassword();
    userService.saveUser(username, password);

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createExtendedPassword")
  public ModelAndView handlePostExtendedPassword(@ModelAttribute("formBean") ExtendedPasswordForm formBean, BindingResult result) {

    mutablePasswordValidator.validateExtendedPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_EXTENDED_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();

    ExtendedPasswordBuilder passwordBuilder = new ExtendedPasswordBuilder();
    MutablePassword mutablePassword = passwordBuilder.createPassword(passwordText, parameter);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createExtendedTimeLockPassword")
  public ModelAndView handlePostExtendedTimeLockPassword(@ModelAttribute("formBean") ExtendedTimeLockPasswordForm formBean, BindingResult result) {

    mutablePasswordValidator.validateExtendedTimeLockPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_EXTENDED_TIMELOCK_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter extended = formBean.getExtendedParameter();
    TimeParameter timeParameter = formBean.getTimeParameter();
    int start = formBean.getStart();
    int end = formBean.getEnd();

    ExtendedTimeLockPasswordBuilder passwordBuilder = new ExtendedTimeLockPasswordBuilder();
    MutablePassword mutablePassword = passwordBuilder.createPassword(passwordText, extended, timeParameter, start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createTimeLockPassword")
  public ModelAndView handlePostTimeLockPassword(@ModelAttribute("formBean") TimeLockPasswordForm formBean, BindingResult result) {

    mutablePasswordValidator.validateTimeLockPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_TIMELOCK_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    int start = formBean.getStart();
    int end = formBean.getEnd();

    TimeLockPasswordBuilder passwordBuilder = new TimeLockPasswordBuilder();
    MutablePassword mutablePassword = passwordBuilder.createPassword(passwordText, parameter, start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createCaesarCipherPassword")
  public ModelAndView handlePostCaesarCipherPassword(@ModelAttribute("formBean") CaesarCipherPasswordForm formBean, BindingResult result) {

    mutablePasswordValidator.validateCaesarCipherPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_CAESAR_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();

    CaesarCipherPasswordBuilder passwordBuilder = new CaesarCipherPasswordBuilder();
    MutablePassword mutablePassword = passwordBuilder.createPassword(passwordText, parameter);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  private TabPanel buildTabPanel(String selectedTab) {
    TabPanel tabPanel = new TabPanel();
    tabPanel.addTab(buildTab("Simple Password", TAB_SIMPLE_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Extended Password", TAB_EXTENDED_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Caesar Cipher Password", TAB_CAESAR_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Extended Time Lock Password", TAB_EXTENDED_TIMELOCK_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Time Lock Password", TAB_TIMELOCK_PASWORD, selectedTab));
    return tabPanel;
  }

  private Tab buildTab(String tabName, String tabUrl, String selectedTab) {
    return new Tab(tabName, tabUrl, tabUrl.equalsIgnoreCase(selectedTab));
  }

}
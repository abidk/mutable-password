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
import abid.password.springmvc.model.CaesarCipherPasswordFormBean;
import abid.password.springmvc.model.ExtendedPasswordFormBean;
import abid.password.springmvc.model.ExtendedTimeLockPasswordFormBean;
import abid.password.springmvc.model.SimplePasswordFormBean;
import abid.password.springmvc.model.Tab;
import abid.password.springmvc.model.TabPanel;
import abid.password.springmvc.model.TimeLockPasswordFormBean;
import abid.password.springmvc.service.UserService;
import abid.password.springmvc.validator.MutablePasswordValidator;
import abid.password.types.CaesarCipherPassword;
import abid.password.types.ExtendedPassword;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.TimeLockPassword;

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
    model.getModelMap().put("SimplePasswordFormBean", new SimplePasswordFormBean());
    model.getModelMap().put("ExtendedPasswordFormBean", new ExtendedPasswordFormBean());
    model.getModelMap().put("ExtendedTimeLockPasswordFormBean", new ExtendedTimeLockPasswordFormBean());
    model.getModelMap().put("TimeLockPasswordFormBean", new TimeLockPasswordFormBean());
    model.getModelMap().put("CaesarCipherPasswordFormBean", new CaesarCipherPasswordFormBean());
    return model;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createSimplePassword")
  public ModelAndView handlePostSimplePassword(
      @ModelAttribute("SimplePasswordFormBean") SimplePasswordFormBean formBean,
      BindingResult result) {

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
  public ModelAndView handlePostExtendedPassword(
      @ModelAttribute("ExtendedPasswordFormBean") ExtendedPasswordFormBean formBean,
      BindingResult result) {

    mutablePasswordValidator.validateExtendedPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_EXTENDED_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    MutablePassword mutablePassword = ExtendedPassword.createPassword(passwordText, parameter);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createExtendedTimeLockPassword")
  public ModelAndView handlePostExtendedTimeLockPassword(
      @ModelAttribute("ExtendedTimeLockPasswordFormBean") ExtendedTimeLockPasswordFormBean formBean, 
      BindingResult result) {

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
    MutablePassword mutablePassword = ExtendedTimeLockPassword.createPassword(passwordText, extended, timeParameter, start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createTimeLockPassword")
  public ModelAndView handlePostTimeLockPassword(
      @ModelAttribute("TimeLockPasswordFormBean") TimeLockPasswordFormBean formBean,
      BindingResult result) {

    mutablePasswordValidator.validateTimeLockPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_TIMELOCK_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    int start = formBean.getStart();
    int end = formBean.getEnd();
    MutablePassword mutablePassword = TimeLockPassword.createPassword(passwordText, parameter, start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    feedbackMessage.addMessage("user.created.success", username);
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createCaesarCipherPassword")
  public ModelAndView handlePostCaesarCipherPassword(
      @ModelAttribute("CaesarCipherPasswordFormBean") CaesarCipherPasswordFormBean formBean,
      BindingResult result) {

    mutablePasswordValidator.validateCaesarCipherPassword(formBean, result);
    if (result.hasErrors()) {
      return handleGetCreateUserTab(TAB_CAESAR_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    MutablePassword mutablePassword = CaesarCipherPassword.createPassword(passwordText, parameter);
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
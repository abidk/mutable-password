package abid.password.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import abid.password.MutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.springmvc.model.CaesarCipherPasswordFormBean;
import abid.password.springmvc.model.ExtendedPasswordFormBean;
import abid.password.springmvc.model.ExtendedTimeLockPasswordFormBean;
import abid.password.springmvc.model.SimplePasswordFormBean;
import abid.password.springmvc.model.Tab;
import abid.password.springmvc.model.TabPanel;
import abid.password.springmvc.model.TimeLockPasswordFormBean;
import abid.password.springmvc.service.UserService;
import abid.password.types.CaesarCipherPassword;
import abid.password.types.ExtendedPassword;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.TimeLockPassword;

@Controller
@RequestMapping("/createUser")
public class CreateUserController {

  public static final String TAB_URL_SIMPLE_PASWORD = "simplepassword";
  public static final String TAB_URL_EXTENDED_PASWORD = "extendedpassword";
  public static final String TAB_URL_CAESAR_PASWORD = "caesarcipherpassword";
  public static final String TAB_URL_EXTENDED_TIMELOCK_PASWORD = "extendedtimelockpassword";
  public static final String TAB_URL_TIMELOCK_PASWORD = "timelockpassword";

  @Autowired
  private UserService userService;
  @Autowired
  private Validator simplePasswordValidator;
  @Autowired
  private Validator extendedPasswordValidator;
  @Autowired
  private Validator extendedTimeLockPasswordValidator;
  @Autowired
  private Validator timeLockPasswordValidator;
  @Autowired
  private Validator caesarCipherPasswordValidator;

  @RequestMapping(value = { "", "/" })
  public ModelAndView handleCreateUser() {
    return new ModelAndView("redirect:/app/createUser/" + TAB_URL_SIMPLE_PASWORD);
  }

  @RequestMapping(value = { "/{selectedTab}" })
  public ModelAndView handleCreateUserTab(@PathVariable String selectedTab) {
    TabPanel tabPanel = new TabPanel();
    tabPanel.addTab(buildTab("Simple Password", TAB_URL_SIMPLE_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Extended Password", TAB_URL_EXTENDED_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Caesar Cipher Password", TAB_URL_CAESAR_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Extended Time Lock Password", TAB_URL_EXTENDED_TIMELOCK_PASWORD, selectedTab));
    tabPanel.addTab(buildTab("Time Lock Password", TAB_URL_TIMELOCK_PASWORD, selectedTab));
    tabPanel.setSelectedTab(selectedTab);

    ModelAndView model = new ModelAndView("createUser");
    model.getModelMap().put("tabs", tabPanel);
    model.getModelMap().put("timeParameter", TimeParameter.values());
    model.getModelMap().put("SimplePasswordFormBean", new SimplePasswordFormBean());
    model.getModelMap().put("ExtendedPasswordFormBean", new ExtendedPasswordFormBean());
    model.getModelMap().put("ExtendedTimeLockPasswordFormBean", new ExtendedTimeLockPasswordFormBean());
    model.getModelMap().put("TimeLockPasswordFormBean", new TimeLockPasswordFormBean());
    model.getModelMap().put("CaesarCipherPasswordFormBean", new CaesarCipherPasswordFormBean());
    return model;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createSimplePassword")
  public ModelAndView handleSimplePassword(@ModelAttribute("SimplePasswordFormBean") SimplePasswordFormBean formBean,
      BindingResult result) {

    simplePasswordValidator.validate(formBean, result);
    if (result.hasErrors()) {
      return handleCreateUserTab(TAB_URL_SIMPLE_PASWORD);
    }

    String username = formBean.getUsername();
    String password = formBean.getPassword();
    userService.saveUser(username, password);

    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createExtendedPassword")
  public ModelAndView handleExtendedPassword(
      @ModelAttribute("ExtendedPasswordFormBean") ExtendedPasswordFormBean formBean, BindingResult result) {

    extendedPasswordValidator.validate(formBean, result);
    if (result.hasErrors()) {
      return handleCreateUserTab(TAB_URL_EXTENDED_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    MutablePassword mutablePassword = ExtendedPassword.createPassword(passwordText, parameter);
    userService.saveUser(username, mutablePassword.getPassword());
    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createExtendedTimeLockPassword")
  public ModelAndView handleExtendedTimeLockPassword(
      @ModelAttribute("ExtendedTimeLockPasswordFormBean") ExtendedTimeLockPasswordFormBean formBean,
      BindingResult result) {

    extendedTimeLockPasswordValidator.validate(formBean, result);
    if (result.hasErrors()) {
      return handleCreateUserTab(TAB_URL_EXTENDED_TIMELOCK_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter extended = formBean.getExtendedParameter();
    TimeParameter timeParameter = formBean.getTimeParameter();
    int start = formBean.getStart();
    int end = formBean.getEnd();
    MutablePassword mutablePassword = ExtendedTimeLockPassword.createPassword(passwordText, extended, timeParameter,
        start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createTimeLockPassword")
  public ModelAndView handleTimeLockPassword(
      @ModelAttribute("TimeLockPasswordFormBean") TimeLockPasswordFormBean formBean, BindingResult result) {

    timeLockPasswordValidator.validate(formBean, result);
    if (result.hasErrors()) {
      return handleCreateUserTab(TAB_URL_TIMELOCK_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    int start = formBean.getStart();
    int end = formBean.getEnd();
    MutablePassword mutablePassword = TimeLockPassword.createPassword(passwordText, parameter, start, end);
    userService.saveUser(username, mutablePassword.getPassword());

    return new ModelAndView("redirect:/app/users/");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/createCaesarCipherPassword")
  public ModelAndView handleCaesarCipherPassword(
      @ModelAttribute("CaesarCipherPasswordFormBean") CaesarCipherPasswordFormBean formBean, BindingResult result) {

    caesarCipherPasswordValidator.validate(formBean, result);
    if (result.hasErrors()) {
      return handleCreateUserTab(TAB_URL_CAESAR_PASWORD);
    }

    String username = formBean.getUsername();
    String passwordText = formBean.getPassword();
    TimeParameter parameter = formBean.getTimeParameter();
    MutablePassword mutablePassword = CaesarCipherPassword.createPassword(passwordText, parameter);
    userService.saveUser(username, mutablePassword.getPassword());

    return new ModelAndView("redirect:/app/users/");
  }

  private Tab buildTab(String tabName, String tabUrl, String selectedTab) {
    return new Tab(tabName, tabUrl, selectedTab.equals(tabUrl));
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public Validator getSimplePasswordValidator() {
    return simplePasswordValidator;
  }

  public void setSimplePasswordValidator(Validator simplePasswordValidator) {
    this.simplePasswordValidator = simplePasswordValidator;
  }

  public Validator getExtendedPasswordValidator() {
    return extendedPasswordValidator;
  }

  public void setExtendedPasswordValidator(Validator extendedPasswordValidator) {
    this.extendedPasswordValidator = extendedPasswordValidator;
  }

  public Validator getExtendedTimeLockPasswordValidator() {
    return extendedTimeLockPasswordValidator;
  }

  public void setExtendedTimeLockPasswordValidator(Validator extendedTimeLockPasswordValidator) {
    this.extendedTimeLockPasswordValidator = extendedTimeLockPasswordValidator;
  }

  public Validator getTimeLockPasswordValidator() {
    return timeLockPasswordValidator;
  }

  public void setTimeLockPasswordValidator(Validator timeLockPasswordValidator) {
    this.timeLockPasswordValidator = timeLockPasswordValidator;
  }

  public Validator getCaesarCipherPasswordValidator() {
    return caesarCipherPasswordValidator;
  }

  public void setCaesarCipherPasswordValidator(Validator caesarCipherPasswordValidator) {
    this.caesarCipherPasswordValidator = caesarCipherPasswordValidator;
  }

}
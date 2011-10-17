package abid.password.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import abid.password.springmvc.model.User;
import abid.password.springmvc.service.UserService;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {
    List<User> users = userService.getUsers();
    model.addAttribute("users", users);
    return "login";
  }

  @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
  public String loginerror(ModelMap model) {
    List<User> users = userService.getUsers();
    model.addAttribute("users", users);
    model.addAttribute("error", "true");
    return "login";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap model) {
    return login(model);
  }

}
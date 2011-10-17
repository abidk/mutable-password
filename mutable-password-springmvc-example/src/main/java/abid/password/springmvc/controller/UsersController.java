package abid.password.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import abid.password.springmvc.model.User;
import abid.password.springmvc.service.UserService;

@Controller
public class UsersController {

  @Autowired
  private UserService userService;

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = { "/users" })
  public ModelAndView handleUsers() {
    ModelAndView model = new ModelAndView("users");
    List<User> users = userService.getUsers();
    model.getModelMap().put("users", users);
    return model;
  }
}
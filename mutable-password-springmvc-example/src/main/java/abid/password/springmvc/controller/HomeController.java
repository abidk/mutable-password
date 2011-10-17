package abid.password.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @RequestMapping(value = { "/", "/index" })
  public ModelAndView showHomePage() {
    ModelAndView model = new ModelAndView("index");
    return model;
  }

  // private String getUser() {
  // Authentication auth =
  // SecurityContextHolder.getContext().getAuthentication();
  // return auth.getName();
  // }
}
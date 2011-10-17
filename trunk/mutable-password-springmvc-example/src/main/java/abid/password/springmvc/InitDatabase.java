package abid.password.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import abid.password.parameters.TimeParameter;
import abid.password.springmvc.service.UserService;
import abid.password.types.ExtendedPassword;
import abid.password.types.SimplePassword;

public class InitDatabase {

  private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

  @Autowired
  private UserService userService;

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public void initDatabase() {
    String user = "Example3";
    String password = ExtendedPassword.createPassword("second_", TimeParameter.SECOND).getPassword();
    userService.saveUser(user, password);
    user = "Example4";
    password = ExtendedPassword.createPassword("minute_", TimeParameter.MINUTE).getPassword();
    userService.saveUser(user, password);
    user = "Example5";
    password = ExtendedPassword.createPassword("hourly_", TimeParameter.HOUR).getPassword();
    userService.saveUser(user, password);
    user = "Example6";
    password = ExtendedPassword.createPassword("day_of_month_", TimeParameter.DAY_OF_MONTH).getPassword();
    userService.saveUser(user, password);
    user = "admin";
    password = new SimplePassword("admin").getPassword();
    userService.saveUser(user, password);
    log.info("Added users!");
  }
}

package abid.password.springmvc.controller;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Test;

import abid.password.springmvc.BaseIntegrationTester;

public class ViewUsersControllerIT extends BaseIntegrationTester {

  @Test
  public void testViewUsersPageContainsTableWithSampleData() {
    beginAt("/index");
    login("admin", "admin");

    assertLinkPresentWithExactText("Users");
    clickLinkWithExactText("Users");
    
    String [] [] expectedTableData = new String [][] {
        {"Username", "Password *" },
        {"Example3", "second_[0-9]*" },
        {"Example4", "minute_[0-9]*" },
        {"Example5", "hourly_[0-9]*" },
        {"Example6", "day_of_month_[0-9]*" },
        {"admin", "admin" }
    };
    assertTableMatch("", expectedTableData);
  }
}

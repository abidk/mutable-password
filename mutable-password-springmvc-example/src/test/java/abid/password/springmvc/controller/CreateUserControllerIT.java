package abid.password.springmvc.controller;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Test;

import abid.password.springmvc.IntegrationTestCase;

public class CreateUserControllerIT extends IntegrationTestCase {

  @Test
  public void testCreateUserPageContainsTabs() {
    beginAt("/index");
    login("admin", "admin");

    assertAndClickLinkWithText("Create User");

    assertLinkPresentWithExactText("Simple Password");
    assertLinkPresentWithExactText("Extended Password");
    assertLinkPresentWithExactText("Caesar Cipher Password");
    assertLinkPresentWithExactText("Extended Time Lock Password");
    assertLinkPresentWithExactText("Time Lock Password");
  }
  
  @Test
  public void testCreateSimplePasswordShouldRedirectToViewUsersPageAndUserExists() {
    beginAt("/index");
    login("admin", "admin");

    assertAndClickLinkWithText("Create User");

    assertFormElementPresent("username");
    setTextField("username", "simple_username");
    assertFormElementPresent("password");
    setTextField("password", "simple_password");
    assertSubmitButtonPresent("", "Save User");
    submit("", "Save User");
    
    // check view users page contains the new user
    assertTextPresent("User \"simple_username\" created successfully.");
    String [] [] expectedTableData = new String [][] {
        {"simple_username", "simple_password" }
    };
    assertTableRowsMatch("", 6, expectedTableData);
  }

}

package abid.password.springmvc.controller;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Test;

import abid.password.springmvc.IntegrationTestCase;

public class HomeControllerIT extends IntegrationTestCase {

  @Test
  public void testHomePageContainsCorrectElements() {
    beginAt("/index");
    login("admin", "admin");

    assertTitleEquals("Mutable Password Spring MVC Example");
    assertTextPresent("Homepage");
    assertLinkPresentWithExactText("Users");
    assertLinkPresentWithExactText("Create User");
    assertLinkPresentWithExactText("Logout");
  }
}

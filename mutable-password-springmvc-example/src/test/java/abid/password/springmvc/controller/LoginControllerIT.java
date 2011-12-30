package abid.password.springmvc.controller;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Test;

import abid.password.springmvc.BaseIntegrationTester;

public class LoginControllerIT extends BaseIntegrationTester {
  
  @Test
  public void testAccessingHomepageReturnsLoginPage() {
    beginAt("/index");

    assertFormElementPresent("j_username");
    assertFormElementPresent("j_password");    
  }
  
  
  @Test
  public void testMenuLinksAppearWhenAuthenticated() {
    beginAt("/index");
    
    assertLinkNotPresentWithExactText("Users");
    assertLinkNotPresentWithExactText("Create User");
    assertLinkNotPresentWithExactText("Logout");

    login("admin", "admin");
    
    assertLinkPresentWithExactText("Users");
    assertLinkPresentWithExactText("Create User");
    assertLinkPresentWithExactText("Logout");
  }
  
  @Test
  public void testLoginPageIsReturnedWhenLoggingOut() {
    beginAt("/index");
    login("admin", "admin");
    
    assertLinkPresentWithExactText("Users");
    assertLinkPresentWithExactText("Create User");
    assertLinkPresentWithExactText("Logout");
    
    clickLinkWithExactText("Logout");
    
    assertFormElementPresent("j_username");
    assertFormElementPresent("j_password");  
  }
}

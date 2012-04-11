/**
 * Copyright 2012 Abid Khalil
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

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Test;

import abid.password.springmvc.IntegrationTestCase;

public class LoginControllerIT extends IntegrationTestCase {
  
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
    
    logout();
    
    assertFormElementPresent("j_username");
    assertFormElementPresent("j_password");  
  }
}

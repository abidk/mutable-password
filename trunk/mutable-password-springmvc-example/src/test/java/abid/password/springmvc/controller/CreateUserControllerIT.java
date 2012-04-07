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

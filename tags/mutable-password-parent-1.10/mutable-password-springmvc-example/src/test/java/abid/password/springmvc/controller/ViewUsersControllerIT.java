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

public class ViewUsersControllerIT extends IntegrationTestCase {

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

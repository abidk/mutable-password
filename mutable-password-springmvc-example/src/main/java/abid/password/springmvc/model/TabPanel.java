/**
 * Copyright 2011 Abid Khalil
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
package abid.password.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class TabPanel {

  private List<Tab> tabs = new ArrayList<Tab>();
  private String selectedTab;

  public void addTab(Tab tab) {
    tabs.add(tab);
  }

  public List<Tab> getTabs() {
    return tabs;
  }

  public void setTabs(List<Tab> tabs) {
    this.tabs = tabs;
  }

  public String getSelectedTab() {
    return selectedTab;
  }

  public void setSelectedTab(String selectedTab) {
    this.selectedTab = selectedTab;
  }

  public boolean isValidTab(String selectedTab) {
    for (Tab tab : tabs) {
      if (tab.getUrlName().equalsIgnoreCase(selectedTab)) {
        return true;
      }
    }
    return false;
  }
}
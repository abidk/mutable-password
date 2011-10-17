package abid.password.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class TabPanel {

  private List<Tab> tabItems = new ArrayList<Tab>();
  private String selectedTab;

  public void addTab(Tab tab) {
    tabItems.add(tab);
  }

  public List<Tab> getTabItems() {
    return tabItems;
  }

  public void setTabItems(List<Tab> tabItems) {
    this.tabItems = tabItems;
  }

  public String getSelectedTab() {
    return selectedTab;
  }

  public void setSelectedTab(String selectedTab) {
    this.selectedTab = selectedTab;
  }

}
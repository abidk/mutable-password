/* Copyright 2009 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package abid.password.parameters;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Time parameters which can be used in the password expressions.
 * 
 * @author Abid
 * 
 */
public enum TimeType {
  // not including a 'second' time enum, as it would make it impossible to guess
  // the password.

  YEAR("year", Calendar.YEAR), 
  MONTH("month", Calendar.MONTH), 
  DAY_OF_MONTH("dayOfMonth", Calendar.DAY_OF_MONTH), 
  HOUR("hour", Calendar.HOUR_OF_DAY), 
  MINUTE("minute", Calendar.MINUTE),
  DAY_OF_WEEK("dayOfWeek", Calendar.DAY_OF_WEEK);
  
  private String textField;
  private int calendarField;

  private TimeType(String textField, int calendarField) {
    this.textField = textField;
    this.calendarField = calendarField;
  }

  public String getTextField() {
    return textField;
  }

  public int getCalendarValue() {
    Calendar calendar = Calendar.getInstance();
    return calendar.get(calendarField);
  }

  public static int getCalendarValue(String type) {
    for (TimeType timeType : TimeType.values()) {
      if (timeType.getTextField().equals(type)) {
        return timeType.getCalendarValue();
      }
    }

    return -1;
  }

  public static Map<String, Integer> getValues() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (TimeType timeType : TimeType.values()) {
      map.put(timeType.getTextField(), timeType.getCalendarValue());
    }
    return map;
  }

  public String toString() {
    return textField + ": " + getCalendarValue();
  }

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    System.out.println(calendar.getTime());

    for (TimeType timePassword : TimeType.values()) {
      System.out.println(timePassword);
    }
  }
}
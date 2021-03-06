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
package abid.password.parameters;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Time parameters which can be used in the password expressions.
 * 
 * @author Abid
 */
public enum TimeParameter {
  YEAR("year", Calendar.YEAR), 
  WEEKOFYEAR("weekOfYear", Calendar.WEEK_OF_YEAR), 
  MONTH("month", Calendar.MONTH), 
  DAY_OF_MONTH("dayOfMonth", Calendar.DAY_OF_MONTH), 
  HOUR("hour", Calendar.HOUR_OF_DAY), 
  MINUTE("minute", Calendar.MINUTE), 
  DAY_OF_WEEK("dayOfWeek", Calendar.DAY_OF_WEEK), 
  SECOND("second", Calendar.SECOND);
  
  private String textField;
  private int calendarField;

  private TimeParameter(String textField, int calendarField) {
    this.textField = textField;
    this.calendarField = calendarField;
  }

  public String getTextField() {
    return textField;
  }

  /**
   * Returns the calendar value for the specified enum.
   * 
   * @return calendar value
   */
  public int getCalendarValue() {
    Calendar calendar = Calendar.getInstance();
    return calendar.get(calendarField);
  }

  /**
   * Gets the calendar value based in the calendar constant. 
   * 
   * @param type
   * @return calendar value
   */
  public static int getCalendarValue(String type) {
    for (TimeParameter timeType : TimeParameter.values()) {
      if (timeType.getTextField().equals(type)) {
        return timeType.getCalendarValue();
      }
    }
    return -1;
  }

  /**
   * Returns parameter values.
   * 
   * @return parameter values
   */
  public static Map<String, Parameter> getParameters() {
    Map<String, Parameter> map = new HashMap<String, Parameter>();
    for (TimeParameter timeType : TimeParameter.values()) {
      map.put(timeType.getTextField(),
          new Parameter(timeType.getCalendarValue()));
    }
    return map;
  }

  @Override
  public String toString() {
    return getCalendarValue() + " (" + textField + ")";
  }

}

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

/**
 * Time parameters which can be used in the password expressions.
 * @author Abid
 *
 */
public enum TimeType {
  // not including a 'second' time enum, as it would make it impossible to guess the password.
  
  YEAR("year", Calendar.YEAR), 
  MONTH("month", Calendar.MONTH), 
  DAY("day", Calendar.DAY_OF_MONTH), 
  HOUR("hour", Calendar.HOUR_OF_DAY), 
  MINUTE("minute", Calendar.MINUTE);

  private String type;
  private int field;

  private TimeType(String type, int calendarType) {
    this.type = type;
    this.field = calendarType;
  }

  public String getType() {
    return type;
  }

  public int getField() {
    return field;
  }

  public int getCalendarValue() {
    Calendar calendar = Calendar.getInstance();
    return calendar.get(field);
  }

  public static int getCalendarValue( String type ) {
    for (TimeType timeType : TimeType.values()) {
      if( timeType.getType().equals(type )) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(timeType.getField());
      }
    }
    
    return -1;
  }
  
  public String toString() {
    return type + ": " + getCalendarValue();
  }

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    System.out.println(calendar.getTime());

    for (TimeType timePassword : TimeType.values()) {
      System.out.println(timePassword);
    }
  }
}
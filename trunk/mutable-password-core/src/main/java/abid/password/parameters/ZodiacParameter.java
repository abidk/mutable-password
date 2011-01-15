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
package abid.password.parameters;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public enum ZodiacParameter {

  ZODIAC("Zodiac");

  private String parameter; 
  private ZodiacParameter( String parameter ) {
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }
  
  public static Zodiac getTodaysZodiac() {
    Calendar today = Calendar.getInstance();    
    return getZodiac(today);
  }

  /**
   * Java month calendar value starts with 0, so it will be incremented by one.
   * 
   * @param date
   * @return ZodiacParameter
   */
  public static Zodiac getZodiac(Calendar date) {
    if( date == null ) {
      return null;
    }
    Zodiac result = null;
    for (Zodiac zodiac : Zodiac.values()) {
      int todayDay = date.get(Calendar.DAY_OF_MONTH);
      int todayMonth = date.get(Calendar.MONTH) + 1;

      if (((todayMonth == zodiac.getEndDate().getMonth()) && (todayDay <= zodiac.getEndDate().getDay()))
          || ((todayMonth == zodiac.getStartDate().getMonth()) && (todayDay >= zodiac.getStartDate().getDay()))) {
        result = zodiac;
        break;
      }
    }
    return result;
  }

  public static Map<String, Parameter> getParameters() {
    Map<String, Parameter> map = new HashMap<String, Parameter>();
    for (ZodiacParameter zodiacParameter : ZodiacParameter.values()) {
      map.put(zodiacParameter.getParameter(), new Parameter(getTodaysZodiac().getSign()) );
    }

    return map;
  }
  
  public static class DayMonth {

    private int day;
    private int month;

    public DayMonth(int day, int month) {
      this.day = day;
      this.month = month;
    }

    public int getDay() {
      return day;
    }

    public int getMonth() {
      return month;
    }
  }

  public static enum Zodiac {
    CAPRICORN("Capricorn", new DayMonth(22,12), new DayMonth(20,1)),
    AQUARIUS("Aquarius", new DayMonth(21,1), new DayMonth(18,2)),
    PISCES("Pisces", new DayMonth(19,2), new DayMonth(20,3)),
    ARIES("Aries", new DayMonth(21,3), new DayMonth(20,4)),
    TAURUS("Taurus", new DayMonth(21,4), new DayMonth(20,5)),
    GEMINI("Gemini", new DayMonth(21,5), new DayMonth(21,6)),
    CANCER("Cancer", new DayMonth(22,6), new DayMonth(22,7)),
    LEO("Leo", new DayMonth(23,7), new DayMonth(22,8)),
    VIRGO("Virgo", new DayMonth(23,8), new DayMonth(22,9)),
    LIBRA("Libra", new DayMonth(23,9), new DayMonth(22,10)),
    SCORPIO("Scorpio", new DayMonth(23,10), new DayMonth(22,11)),
    SAGITTARIUS("Sagittarius", new DayMonth(23,11), new DayMonth(21,12));
    
    private String sign;
    private DayMonth startDate;
    private DayMonth endDate;

    
    Zodiac(String sign, DayMonth startDate, DayMonth endDate) {
      this.sign = sign;
      this.startDate = startDate;
      this.endDate = endDate;
    }

    public String getSign() {
      return sign;
    }

    public DayMonth getStartDate() {
      return startDate;
    }

    public DayMonth getEndDate() {
      return endDate;
    }
  }

}

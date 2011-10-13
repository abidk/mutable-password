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

import junit.framework.TestCase;
import abid.password.parameters.ZodiacParameter.DayMonth;
import abid.password.parameters.ZodiacParameter.Zodiac;

public class ZodiacParameterTest extends TestCase {

  public static final int JAN = 0;
  public static final int FEB = 1;
  public static final int MAR = 2;
  public static final int APR = 3;
  public static final int MAY = 4;
  public static final int JUN = 5;
  public static final int JUL = 6;
  public static final int AUG = 7;
  public static final int SEP = 8;
  public static final int OCT = 9;
  public static final int NOV = 10;
  public static final int DEC = 11;

  public void testCapricorn() {
    assertEquals("Capricorn", Zodiac.CAPRICORN.getSign());
    
    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 22);
    startCal.set(Calendar.MONTH, DEC);
    assertEquals(Zodiac.CAPRICORN, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, JAN);
    assertEquals(Zodiac.CAPRICORN, ZodiacParameter.getZodiac(endCal));
  }

  public void testAquarius() {
    assertEquals("Aquarius", Zodiac.AQUARIUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, JAN);
    assertEquals(Zodiac.AQUARIUS, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 18);
    endCal.set(Calendar.MONTH, FEB);
    assertEquals(Zodiac.AQUARIUS, ZodiacParameter.getZodiac(endCal));
  }

  public void testPisces() {
    assertEquals("Pisces", Zodiac.PISCES.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 19);
    startCal.set(Calendar.MONTH, FEB);
    assertEquals(Zodiac.PISCES, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, MAR);
    assertEquals(Zodiac.PISCES, ZodiacParameter.getZodiac(endCal));
  }

  public void testAries() {
    assertEquals("Aries", Zodiac.ARIES.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, MAR);
    assertEquals(Zodiac.ARIES, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, APR);
    assertEquals(Zodiac.ARIES, ZodiacParameter.getZodiac(endCal));
  }

  public void testTaurus() {
    assertEquals("Taurus", Zodiac.TAURUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, APR);
    assertEquals(Zodiac.TAURUS, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, MAY);
    assertEquals(Zodiac.TAURUS, ZodiacParameter.getZodiac(endCal));
  }

  public void testGemini() {
    assertEquals("Gemini", Zodiac.GEMINI.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, MAY);
    assertEquals(Zodiac.GEMINI, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 21);
    endCal.set(Calendar.MONTH, JUN);
    assertEquals(Zodiac.GEMINI, ZodiacParameter.getZodiac(endCal));
  }

  public void testCancer() {
    assertEquals("Cancer", Zodiac.CANCER.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 22);
    startCal.set(Calendar.MONTH, JUN);
    assertEquals(Zodiac.CANCER, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, JUL);
    assertEquals(Zodiac.CANCER, ZodiacParameter.getZodiac(endCal));
  }

  public void testLeo() {
    assertEquals("Leo", Zodiac.LEO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, JUL);
    assertEquals(Zodiac.LEO, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, AUG);
    assertEquals(Zodiac.LEO, ZodiacParameter.getZodiac(endCal));
  }

  public void testVirgo() {
    assertEquals("Virgo", Zodiac.VIRGO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, AUG);
    assertEquals(Zodiac.VIRGO, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, SEP);
    assertEquals(Zodiac.VIRGO, ZodiacParameter.getZodiac(endCal));
  }

  public void testLibra() {
    assertEquals("Libra", Zodiac.LIBRA.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, SEP);
    assertEquals(Zodiac.LIBRA, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, OCT);
    assertEquals(Zodiac.LIBRA, ZodiacParameter.getZodiac(endCal));
  }

  public void testScorpio() {
    assertEquals("Scorpio", Zodiac.SCORPIO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, OCT);
    assertEquals(Zodiac.SCORPIO, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, NOV);
    assertEquals(Zodiac.SCORPIO, ZodiacParameter.getZodiac(endCal));
  }

  public void testSagittarius() {
    assertEquals("Sagittarius", Zodiac.SAGITTARIUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, NOV);
    assertEquals(Zodiac.SAGITTARIUS, ZodiacParameter.getZodiac(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 21);
    endCal.set(Calendar.MONTH, DEC);
    assertEquals(Zodiac.SAGITTARIUS, ZodiacParameter.getZodiac(endCal));
  }

  public void testDayMonthInnerClass( ) {
    DayMonth dayMonth = new DayMonth(1, 2);
    assertEquals(1, dayMonth.getDay());
    assertEquals(2, dayMonth.getMonth());
  }
  
  public void testTodaysZodiac( ) {
    Calendar today = Calendar.getInstance();
    Zodiac todayZodiac  = ZodiacParameter.getZodiac(today);
    assertEquals(todayZodiac, ZodiacParameter.getTodaysZodiac());
  }
  
  public void testNullValue() {
    assertNull(ZodiacParameter.getZodiac(null));
  }
  
  public void testToStringIsNotNull( ) {
    assertNotNull(Zodiac.SAGITTARIUS.toString());
  }
  
  public void testAllZaodiacParameterToString( ) {
    for( ZodiacParameter parameter : ZodiacParameter.values() ) {
      assertNotNull(parameter.toString());
    }
  }
} 

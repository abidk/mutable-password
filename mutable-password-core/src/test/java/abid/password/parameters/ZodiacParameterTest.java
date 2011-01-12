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

public class ZodiacParameterTest extends TestCase {

  public static int JAN = 0;
  public static int FEB = 1;
  public static int MAR = 2;
  public static int APR = 3;
  public static int MAY = 4;
  public static int JUN = 5;
  public static int JUL = 6;
  public static int AUG = 7;
  public static int SEP = 8;
  public static int OCT = 9;
  public static int NOV = 10;
  public static int DEC = 11;

  public void testCapricorn() {
    assertEquals("Capricorn", ZodiacParameter.CAPRICORN.getSign());
    
    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 22);
    startCal.set(Calendar.MONTH, DEC);
    assertEquals(ZodiacParameter.CAPRICORN, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, JAN);
    assertEquals(ZodiacParameter.CAPRICORN, ZodiacParameter.getSign(endCal));
  }

  public void testAquarius() {
    assertEquals("Aquarius", ZodiacParameter.AQUARIUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, JAN);
    assertEquals(ZodiacParameter.AQUARIUS, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 18);
    endCal.set(Calendar.MONTH, FEB);
    assertEquals(ZodiacParameter.AQUARIUS, ZodiacParameter.getSign(endCal));
  }

  public void testPisces() {
    assertEquals("Pisces", ZodiacParameter.PISCES.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 19);
    startCal.set(Calendar.MONTH, FEB);
    assertEquals(ZodiacParameter.PISCES, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, MAR);
    assertEquals(ZodiacParameter.PISCES, ZodiacParameter.getSign(endCal));
  }

  public void testAries() {
    assertEquals("Aries", ZodiacParameter.ARIES.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, MAR);
    assertEquals(ZodiacParameter.ARIES, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, APR);
    assertEquals(ZodiacParameter.ARIES, ZodiacParameter.getSign(endCal));
  }

  public void testTaurus() {
    assertEquals("Taurus", ZodiacParameter.TAURUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, APR);
    assertEquals(ZodiacParameter.TAURUS, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 20);
    endCal.set(Calendar.MONTH, MAY);
    assertEquals(ZodiacParameter.TAURUS, ZodiacParameter.getSign(endCal));
  }

  public void testGemini() {
    assertEquals("Gemini", ZodiacParameter.GEMINI.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 21);
    startCal.set(Calendar.MONTH, MAY);
    assertEquals(ZodiacParameter.GEMINI, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 21);
    endCal.set(Calendar.MONTH, JUN);
    assertEquals(ZodiacParameter.GEMINI, ZodiacParameter.getSign(endCal));
  }

  public void testCancer() {
    assertEquals("Cancer", ZodiacParameter.CANCER.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 22);
    startCal.set(Calendar.MONTH, JUN);
    assertEquals(ZodiacParameter.CANCER, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, JUL);
    assertEquals(ZodiacParameter.CANCER, ZodiacParameter.getSign(endCal));
  }

  public void testLeo() {
    assertEquals("Leo", ZodiacParameter.LEO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, JUL);
    assertEquals(ZodiacParameter.LEO, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, AUG);
    assertEquals(ZodiacParameter.LEO, ZodiacParameter.getSign(endCal));
  }

  public void testVirgo() {
    assertEquals("Virgo", ZodiacParameter.VIRGO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, AUG);
    assertEquals(ZodiacParameter.VIRGO, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, SEP);
    assertEquals(ZodiacParameter.VIRGO, ZodiacParameter.getSign(endCal));
  }

  public void testLibra() {
    assertEquals("Libra", ZodiacParameter.LIBRA.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, SEP);
    assertEquals(ZodiacParameter.LIBRA, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, OCT);
    assertEquals(ZodiacParameter.LIBRA, ZodiacParameter.getSign(endCal));
  }

  public void testScorpio() {
    assertEquals("Scorpio", ZodiacParameter.SCORPIO.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, OCT);
    assertEquals(ZodiacParameter.SCORPIO, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 22);
    endCal.set(Calendar.MONTH, NOV);
    assertEquals(ZodiacParameter.SCORPIO, ZodiacParameter.getSign(endCal));
  }

  public void testSagittarius() {
    assertEquals("Sagittarius", ZodiacParameter.SAGITTARIUS.getSign());

    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.DAY_OF_MONTH, 23);
    startCal.set(Calendar.MONTH, NOV);
    assertEquals(ZodiacParameter.SAGITTARIUS, ZodiacParameter.getSign(startCal));

    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.DAY_OF_MONTH, 21);
    endCal.set(Calendar.MONTH, DEC);
    assertEquals(ZodiacParameter.SAGITTARIUS, ZodiacParameter.getSign(endCal));
  }

  public void testDayMonthInnerClass( ) {
    ZodiacParameter.DayMonth dayMonth = new ZodiacParameter.DayMonth(1, 2);
    assertEquals(1, dayMonth.getDay());
    assertEquals(2, dayMonth.getMonth());
  }
  
  public void testTodaysZodiac( ) {
    Calendar today = Calendar.getInstance();
    ZodiacParameter todayZodiac  = ZodiacParameter.getSign(today);
    assertEquals(todayZodiac, ZodiacParameter.getTodaysSign());
  }
  
  public void testNullValue() {
    assertEquals(null, ZodiacParameter.getSign(null));
  }
} 

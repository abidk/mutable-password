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

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeParameterTest {

  @Test
  public void getCalendarValueShouldReturnCorrectValue() {
    int val = TimeParameter.getCalendarValue("madeUpType");
    assertEquals(-1, val);

    val = TimeParameter.getCalendarValue(TimeParameter.HOUR.getTextField());
    assertEquals(TimeParameter.HOUR.getCalendarValue(), val);
  }

  @Test
  public void testTimeParameterValues() {
    TimeParameter param = TimeParameter.HOUR;
    assertTrue(param.toString().contains(param.getTextField()));
  }
}

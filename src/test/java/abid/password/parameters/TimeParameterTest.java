package abid.password.parameters;

import junit.framework.TestCase;

public class TimeParameterTest extends TestCase {

  public void testWrongCalendarValue() {
    int val = TimeParameter.getCalendarValue("madeUpType");
    assertEquals(-1, val);
  }

  public void testCalendarValue() {
    int val = TimeParameter.getCalendarValue(TimeParameter.HOUR.getTextField());
    assertEquals(TimeParameter.HOUR.getCalendarValue(), val);
  }

  public void testTimeParameterValues() {
    TimeParameter param = TimeParameter.HOUR;
    param.toString();
  }
}

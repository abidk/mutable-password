package abid.password;

import junit.framework.TestCase;
import abid.password.parameters.TimeType;
import abid.password.types.ExtendedPassword;

public class ExtendedPasswordTest extends TestCase {
  
  public void testExtendedPassword() {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abida" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testExtendedPassword2() {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", "year+year");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + timeType.getCalendarValue());
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testExtendedPassword3() {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = ExtendedPassword.createPassword("abid", "year+1.5");

    String confirmPassword = "abid" + (timeType.getCalendarValue() + 1.5);
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }
}

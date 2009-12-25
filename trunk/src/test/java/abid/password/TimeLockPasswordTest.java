package abid.password;

import java.util.Calendar;

import abid.password.parameters.TimeType;
import abid.password.types.TimeLockPassword;
import junit.framework.TestCase;

public class TimeLockPasswordTest extends TestCase {

  public void testTimeLockPassword() {
    String confirmPassword = "abid";
    MutablePassword mutatingPassword = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);
    assertEquals(true, mutatingPassword.confirmPassword(confirmPassword));
    
    MutablePassword mutatingPassword2 = TimeLockPassword.createPassword("abid", TimeType.HOUR, -2, -1);
    assertEquals(false, mutatingPassword2.confirmPassword(confirmPassword));

    String wrongPassword = "diba";
    MutablePassword mutatingPassword3 = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);
    assertEquals(false, mutatingPassword3.confirmPassword(wrongPassword));
  }
  
  public void testDayOfWeekPassword() {
    //Calendar.SUNDAY = 1
    //Calendar.MONDAY = 2
    //Calendar.TUESDAY = 3
    //Calendar.WEDNESDAY = 4
    //Calendar.THURSDAY = 5
    //Calendar.FRIDAY = 6
    //Calendar.SATURDAY = 7
    String confirmPassword = "dayOfWeek";
    String wrongPassword = "wrongDayOfWeek";
    MutablePassword mutatingPassword = TimeLockPassword.createPassword("dayOfWeek", TimeType.DAY_OF_WEEK, Calendar.SUNDAY, Calendar.SATURDAY);
    assertEquals(true, mutatingPassword.confirmPassword(confirmPassword));
    assertEquals(false, mutatingPassword.confirmPassword(wrongPassword));
  }
  
}

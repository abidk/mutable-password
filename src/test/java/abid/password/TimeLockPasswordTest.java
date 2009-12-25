package abid.password;

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
}

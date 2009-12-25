package abid.password;

import abid.password.types.ShiftPassword;
import junit.framework.TestCase;

public class ShiftPasswordTest extends TestCase {

  public void testShiftPassword() {
    Password dynamicPassword = ShiftPassword.createPassword("abid", 1);

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testShiftPassword2() {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("abid", 1);

    assertEquals("shift", dynamicPassword.getType());

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }
}

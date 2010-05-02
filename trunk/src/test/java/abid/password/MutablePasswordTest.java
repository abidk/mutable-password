package abid.password;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import abid.password.types.ExtendedTimeLockPassword;
import abid.password.types.ShiftPassword;

public class MutablePasswordTest extends TestCase {
  public void testNullBlock() {
    String passwordText = "pass";
    MutablePassword password = new ShiftPassword(passwordText, null);
    assertNull(password.getExpression());
    assertNull(password.getType());
    assertEquals(passwordText, password.getText());
    assertNull(password.getMutableBlock());
  }

  public void testStartOfExpression( ) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    Password pass = new ExtendedTimeLockPassword("abidextendedTimeLock{year}]");
  }
}

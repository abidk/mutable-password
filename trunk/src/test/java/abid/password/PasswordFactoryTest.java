package abid.password;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import abid.password.Password;
import abid.password.parameters.TimeType;
import abid.password.types.ShiftPassword;
import abid.password.types.SimplePassword;
import abid.password.types.ExtendedPassword;
import abid.password.types.TimeLockPassword;

public class PasswordFactoryTest extends TestCase {

  public void testTimeLockPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p1 = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);
    Password m1 = PasswordFactory.getInstance(p1.getPassword());
    
    // check if the correct class is found
    assertEquals(p1.getClass(), m1.getClass());
    assertEquals(p1.getPassword(), m1.getPassword());
  }

  public void testExtendedPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p2 = ExtendedPassword.createPassword("abid", TimeType.HOUR);
    Password m2 = PasswordFactory.getInstance(p2.getPassword());
    assertEquals(p2.getClass(), m2.getClass());
    assertEquals(p2.getPassword(), m2.getPassword());
  }

  public void testShiftPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p3 = ShiftPassword.createPassword("abid", 1);
    Password m3 = PasswordFactory.getInstance(p3.getPassword());
    assertEquals(p3.getClass(), m3.getClass());
    assertEquals(p3.getPassword(), m3.getPassword());
  }

  public void testSimplePasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {

    Password p4 = new SimplePassword("abid");
    Password m4 = PasswordFactory.getInstance(p4.getPassword());
    assertEquals(p4.getClass(), m4.getClass());
    assertEquals(p4.getPassword(), m4.getPassword());
  }
}

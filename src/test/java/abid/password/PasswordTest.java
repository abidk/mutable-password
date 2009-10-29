package abid.password;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import abid.password.Password;
import abid.password.parameters.TimeType;
import abid.password.types.ShiftPassword;
import abid.password.types.SimplePassword;
import abid.password.types.TimePassword;
import abid.password.types.TimeLockPassword;

public class PasswordTest extends TestCase {

  public void testTimePassword() {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = TimePassword.createPassword("abid", timeType);

    String confirmPassword = "abid" + timeType.getCalendarValue();
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abida" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }
  
  public void testTimePassword2() {
    TimeType timeType = TimeType.YEAR;
    Password dynamicPassword = TimePassword.createPassword("abid", "year+1.5");

    String confirmPassword = "abid" + ( timeType.getCalendarValue() + 1.5 );
    //System.out.println(confirmPassword);
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid" + timeType.getCalendarValue();
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }
  

  public void testShiftPassword() {
    Password dynamicPassword = ShiftPassword.createPassword("abid", 1);

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testUnknownPasswordType() {
    MutablePassword dynamicPassword = ShiftPassword.createPassword("abid", 1);

    assertEquals("shift", dynamicPassword.getType());

    String confirmPassword = "bcje";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "abid";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    String password = "abid";
    Password dynamicPassword = PasswordFactory.getInstance(password);

    String confirmPassword = "abid";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword2() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    String password = "abid[";
    Password dynamicPassword = PasswordFactory.getInstance(password);

    String confirmPassword = "abid[";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword3() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password dynamicPassword = PasswordFactory.getInstance("abid[]");

    String confirmPassword = "abid[]";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword4() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password dynamicPassword = PasswordFactory.getInstance("abid[{]");

    String confirmPassword = "abid[{]";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testSimplePassword5() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Password dynamicPassword = PasswordFactory.getInstance("ab[id}]");

    String confirmPassword = "ab[id}]";
    assertEquals(true, dynamicPassword.confirmPassword(confirmPassword));

    String wrongPassword = "bcje";
    assertEquals(false, dynamicPassword.confirmPassword(wrongPassword));
  }

  public void testTimeRangePassword() {
    String confirmPassword = "abid";
    MutablePassword mutatingPassword = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);

    assertEquals(true, mutatingPassword.confirmPassword(confirmPassword));
    MutablePassword mutatingPassword2 = TimeLockPassword.createPassword("abid", TimeType.HOUR, -2, -1);
    assertEquals(false, mutatingPassword2.confirmPassword(confirmPassword));

    String wrongPassword = "diba";
    MutablePassword mutatingPassword3 = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);
    assertEquals(false, mutatingPassword3.confirmPassword(wrongPassword));
  }

  public void testPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException,
      NoSuchMethodException {
    Password p1 = TimeLockPassword.createPassword("abid", TimeType.HOUR, 0, 24);
    Password m1 = PasswordFactory.getInstance(p1.getPassword());
    assertEquals(p1.getClass(), m1.getClass());
    assertEquals(p1.getPassword(), m1.getPassword());

    Password p2 = TimePassword.createPassword("abid", TimeType.HOUR);
    Password m2 = PasswordFactory.getInstance(p2.getPassword());
    assertEquals(p2.getClass(), m2.getClass());
    assertEquals(p2.getPassword(), m2.getPassword());

    Password p3 = ShiftPassword.createPassword("abid", 1);
    Password m3 = PasswordFactory.getInstance(p3.getPassword());
    assertEquals(p3.getClass(), m3.getClass());
    assertEquals(p3.getPassword(), m3.getPassword());

    Password p4 = new SimplePassword("abid");
    Password m4 = PasswordFactory.getInstance(p4.getPassword());
    assertEquals(p4.getClass(), m4.getClass());
    assertEquals(p4.getPassword(), m4.getPassword());

  }
}

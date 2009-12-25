package abid.password;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

public class SimplePasswordTest extends TestCase {

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
}

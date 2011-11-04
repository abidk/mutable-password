package abid.password.types;

import junit.framework.TestCase;

public class PasswordInstantiationExceptionTest extends TestCase {

  public void testPasswordException() {
    try {
      throw new PasswordInstantiationException("some text", new Exception("some exception"));
    } catch (Exception e) {
      assertEquals("some text", e.getMessage());
      assertEquals("some exception", e.getCause().getMessage());
    }
  }
  
}

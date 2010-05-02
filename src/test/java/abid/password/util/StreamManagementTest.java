package abid.password.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

public class StreamManagementTest extends TestCase {

  public void testStreamToString() throws IOException {
    String testStr = "test";
    InputStream in = new ByteArrayInputStream(testStr.getBytes());
    String convertedStr = StreamManagement.convertStreamToString(in);
    in.close();
    assertEquals(testStr, convertedStr);
  }

  /*
   * Workaround to get 100% coverage on a private constructor.
   */
  public void testStream() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final Class<?> cls = StreamManagement.class;
    final Constructor<?> c = cls.getDeclaredConstructors()[0];
    c.setAccessible(true);
    final Object n = c.newInstance((Object[]) null);
  }

}

package abid.password;

import java.lang.reflect.InvocationTargetException;

import abid.password.type.NewTypePassword;
import abid.password.types.SimplePassword;
import junit.framework.TestCase;

public class CreateNewPasswordTypeTest extends TestCase {

  public void testNewPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    NewTypePassword password = (NewTypePassword) NewTypePassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());

    // class is not registered with factory so the above line will not find the
    // appropriate class.
    assertNotSame(NewTypePassword.class, m1.getClass());
    
    // The factory should return a SimplePassword object
    assertEquals(SimplePassword.class, m1.getClass());

    //System.out.println(m1.getPassword());
  }

  public void testRegisteredNewPasswordType() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    // add the password to the factory
    PasswordFactory.addMutablePassword(NewTypePassword.class);

    NewTypePassword password = (NewTypePassword) NewTypePassword.createPassword("something");

    Password m1 = PasswordFactory.getInstance(password.getPassword());
    
    // The factory should return a NewTypePassword object
    assertEquals(NewTypePassword.class, m1.getClass());
  }

}

package abid.password.types;

import static org.fest.assertions.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import abid.password.MutablePassword;
import abid.password.evaluator.EvaluationException;

public class HashedPasswordBuilderTest {

  private HashedPasswordBuilder builder;

  @Before
  public void setUp() {
    builder = new HashedPasswordBuilder();
  }

  @Test
  public void createPasswordWithSaltShouldOutputExactPassword() throws EvaluationException {
    MutablePassword password = builder.createPassword("123", "123".getBytes());
    assertThat(password.getPassword()).isEqualTo("a4a16831681e9f2084cf7f23cf4a54b2b0e97ba8316aadcf0625a43d852377[hashed{SHA-256,32,313233}]");
  }

  @Test
  public void createPasswordShouldCorrectlyFormatPassword() throws EvaluationException {
    MutablePassword password = builder.createPassword("123");
    assertThat(password.getPassword()).matches("^[A-z0-9]+\\[hashed\\{[A-z0-9,-]+\\}\\]$");
  }
  
  @Test
  public void createPasswordWithSpecifiedParamtersShouldCorrectlyFormatPassword() throws EvaluationException {
    builder.setAlgorithm("MD5");
    builder.setDigestLength(16);
    builder.setSaltLength(1);
    MutablePassword password = builder.createPassword("123");
    
    assertThat(password.getPassword()).matches("^[A-z0-9]+\\[hashed\\{MD5,16,[A-z0-9]+\\}\\]$");
  }
}

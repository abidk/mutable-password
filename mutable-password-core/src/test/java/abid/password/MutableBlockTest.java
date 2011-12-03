package abid.password;

import static org.junit.Assert.*;

import org.junit.Test;

public class MutableBlockTest {

  @Test
  public void testAccessorAndMutators() {
    MutableBlock block = new MutableBlock("type", "year");
    assertEquals("type", block.getType());
    assertEquals("year", block.getExpression());

    String password = "[extendedTimeLock{zodiac}]";
    block = new MutableBlock(password);
    assertEquals("extendedTimeLock", block.getType());
    assertEquals("zodiac", block.getExpression());
  }
}

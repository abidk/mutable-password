package abid.password;

import static org.junit.Assert.*;

import org.junit.Test;

public class MutableBlockTest {

  @Test
  public void testAccessorAndMutators() {
    MutableBlock block = new MutableBlock("type", "year");
    assertEquals("type", block.getType());
    assertEquals("year", block.getExpression());

    block = new MutableBlock("[extendedTimeLock{zodiac}]");
    assertEquals("extendedTimeLock", block.getType());
    assertEquals("zodiac", block.getExpression());
  }
}

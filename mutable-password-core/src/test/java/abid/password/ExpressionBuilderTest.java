package abid.password;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExpressionBuilderTest {

  private ExpressionBuilder builder;

  @Before
  public void setUp() {
    builder = new ExpressionBuilder();
  }

  @Test
  public void addShouldBuildExpressionCorrectly() {
    assertEquals("+2", builder.clear().add(2).toString());
    assertEquals("1+2", builder.clear().add(1, 2).toString());
    assertEquals("1+(2+3)", builder.clear().value(1).add(new ExpressionBuilder().add(2, 3)).toString());
    assertEquals("1+2", builder.clear().value(1).add().value(2).toString());
  }

  @Test
  public void subtractShouldBuildExpressionCorrectly() {
    assertEquals("-2", builder.clear().subtract(2).toString());
    assertEquals("1-2", builder.clear().subtract(1, 2).toString());
    assertEquals("1-(2-3)", builder.clear().value(1).subtract(new ExpressionBuilder().subtract(2, 3)).toString());
    assertEquals("1-2", builder.clear().value(1).subtract().value(2).toString());

  }

  @Test
  public void divideShouldBuildExpressionCorrectly() {
    assertEquals("/2", builder.clear().divide(2).toString());
    assertEquals("1/2", builder.clear().divide(1, 2).toString());
    assertEquals("1/(2/3)", builder.clear().value(1).divide(new ExpressionBuilder().divide(2, 3)).toString());
    assertEquals("1/2", builder.clear().value(1).divide().value(2).toString());
  }

  @Test
  public void multiplyShouldBuildExpressionCorrectly() {
    assertEquals("*2", builder.clear().multiply(2).toString());
    assertEquals("1*2", builder.clear().multiply(1, 2).toString());
    assertEquals("1*(2*3)", builder.clear().value(1).multiply(new ExpressionBuilder().multiply(2, 3)).toString());
    assertEquals("1*2", builder.clear().value(1).multiply().value(2).toString());
  }

  @Test
  public void bracketsShouldBuildCorrectly() {
    assertEquals("(1)", builder.clear().openBracket().value(1).closeBracket().toString());
  }

  @Test
  public void symbolShouldBuildCorrectly() {
    assertEquals("1^2", builder.clear().value(1).symbol("^").value(2).toString());
  }

  @Test
  public void logicalOperatorsShouldBuildCorrectly() {
    builder.clear().value("year").greaterEquals().value(1).and().value("year").lessEquals().value(2);
    assertEquals("year>=1&&year<=2", builder.toString());

    builder.clear().value("year").greaterEquals().value(1).or().value("year").lessEquals().value(2);
    assertEquals("year>=1||year<=2", builder.toString());

    builder.clear().value("year").notEqual().value(2);
    assertEquals("year!=2", builder.toString());

    builder.clear().value("year").greater().value(1).and().value("year").less().value(2);
    assertEquals("year>1&&year<2", builder.toString());

    builder.clear().value("year").equals().value(2);
    assertEquals("year==2", builder.toString());
  }

  @Test
  public void clearShouldRemoveExpression() {
    builder.openBracket().value(1).closeBracket();

    assertEquals("(1)", builder.toString());
    assertEquals("", builder.clear().toString());
  }
}

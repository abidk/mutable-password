/**
 * Copyright 2012 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package abid.password;

/**
 * Simple expression builder.
 * 
 * @author Abid
 * 
 */
public class ExpressionBuilder {

  public static final String OPEN_BRACKET = "(";
  public static final String CLOSE_BRACKET = ")";

  public static final String ADD_SYMBOL = "+";
  public static final String SUBTRACT_SYMBOL = "-";
  public static final String DIVIDE_SYMBOL = "/";
  public static final String MULTIPLY_SYMBOL = "*";

  public static final String AND_SYMBOL = "&&";
  public static final String OR_SYMBOL = "||";

  public static final String GREATER_THAN_SYMBOL = ">";
  public static final String GREATER_EQUALS_SYMBOL = ">=";
  public static final String LESS_THAN_SYMBOL = "<";
  public static final String LESS_EQUALS_SYMBOL = "<=";
  public static final String EQUALS_SYMBOL = "==";
  public static final String NOT_EQUAL_SYMBOL = "!=";

  private StringBuilder expression = new StringBuilder();

  /**
   * Should be a single value String, integer, float etc.
   * 
   * @param value
   * @return expression.
   */
  public ExpressionBuilder value(Object value) {
    expression.append(value);
    return this;
  }

  /**
   * Add '&&' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder and() {
    expression.append(AND_SYMBOL);
    return this;
  }

  /**
   * Add '||' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder or() {
    expression.append(OR_SYMBOL);
    return this;
  }

  /**
   * Add '>' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder greater() {
    expression.append(GREATER_THAN_SYMBOL);
    return this;
  }

  /**
   * Add '>=' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder greaterEquals() {
    expression.append(GREATER_EQUALS_SYMBOL);
    return this;
  }

/**
   * Add '<' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder less() {
    expression.append(LESS_THAN_SYMBOL);
    return this;
  }

  /**
   * Add '<=' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder lessEquals() {
    expression.append(LESS_EQUALS_SYMBOL);
    return this;
  }

  /**
   * Add '==' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder equals() {
    expression.append(EQUALS_SYMBOL);
    return this;
  }

  /**
   * Add '!=' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder notEqual() {
    expression.append(NOT_EQUAL_SYMBOL);
    return this;
  }

  /**
   * Add '+' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder add() {
    expression.append(ADD_SYMBOL);
    return this;
  }

  /**
   * Creates expression to add single input value i.e. +1
   * 
   * @param value
   * @return expression.
   */
  public ExpressionBuilder add(Object value) {
    expression.append(ADD_SYMBOL);
    expression.append(value);
    return this;
  }

  /**
   * Creates expression to add two input values.
   * 
   * @param value1
   * @param value2
   * @return expression.
   */
  public ExpressionBuilder add(Object value1, Object value2) {
    expression.append(value1);
    expression.append(ADD_SYMBOL);
    expression.append(value2);
    return this;
  }

  /**
   * Surrounds and adds the input expression i.e. 1+(2+3).
   * 
   * @param exp
   *          Expression to add.
   * @return expression.
   */
  public ExpressionBuilder add(ExpressionBuilder exp) {
    expression.append(ADD_SYMBOL);
    expression.append(OPEN_BRACKET);
    expression.append(exp);
    expression.append(CLOSE_BRACKET);
    return this;
  }

  /**
   * Add '-' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder subtract() {
    expression.append(SUBTRACT_SYMBOL);
    return this;
  }

  /**
   * Creates expression to subtract single input value i.e. -1
   * 
   * @param value
   * @return expression.
   */
  public ExpressionBuilder subtract(Object value) {
    expression.append(SUBTRACT_SYMBOL);
    expression.append(value);
    return this;
  }

  /**
   * Creates expression to subtract two input values.
   * 
   * @param value1
   * @param value2
   * @return expression.
   */
  public ExpressionBuilder subtract(Object value1, Object value2) {
    expression.append(value1);
    expression.append(SUBTRACT_SYMBOL);
    expression.append(value2);
    return this;
  }

  /**
   * Surrounds and subtracts the input expression i.e. 1-(2+3).
   * 
   * @param exp
   *          Expression to subtract.
   * @return expression.
   */
  public ExpressionBuilder subtract(ExpressionBuilder exp) {
    expression.append(SUBTRACT_SYMBOL);
    expression.append(OPEN_BRACKET);
    expression.append(exp);
    expression.append(CLOSE_BRACKET);
    return this;
  }

  /**
   * Add '/' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder divide() {
    expression.append(DIVIDE_SYMBOL);
    return this;
  }

  /**
   * Creates expression to divide single input value i.e. /1
   * 
   * @param value
   * @return expression.
   */
  public ExpressionBuilder divide(Object value) {
    expression.append(DIVIDE_SYMBOL);
    expression.append(value);
    return this;
  }

  /**
   * Creates expression to divide two input values.
   * 
   * @param value1
   * @param value2
   * @return expression.
   */
  public ExpressionBuilder divide(Object value1, Object value2) {
    expression.append(value1);
    expression.append(DIVIDE_SYMBOL);
    expression.append(value2);
    return this;
  }

  /**
   * Surrounds and divides the input expression i.e. 1/(2+3).
   * 
   * @param exp
   *          Expression to divide.
   * @return expression.
   */
  public ExpressionBuilder divide(ExpressionBuilder exp) {
    expression.append(DIVIDE_SYMBOL);
    expression.append(OPEN_BRACKET);
    expression.append(exp);
    expression.append(CLOSE_BRACKET);
    return this;
  }

  /**
   * Add '*' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder multiply() {
    expression.append(MULTIPLY_SYMBOL);
    return this;
  }

  /**
   * Creates expression to multiply single input value i.e. *1
   * 
   * @param value
   * @return expression.
   */
  public ExpressionBuilder multiply(Object value) {
    expression.append(MULTIPLY_SYMBOL);
    expression.append(value);
    return this;
  }

  /**
   * Creates expression to multiply two input values.
   * 
   * @param value1
   * @param value2
   * @return expression.
   */
  public ExpressionBuilder multiply(Object value1, Object value2) {
    expression.append(value1);
    expression.append(MULTIPLY_SYMBOL);
    expression.append(value2);
    return this;
  }

  /**
   * Surrounds and multiplies the input expression i.e. 1*(2+3).
   * 
   * @param exp
   *          Expression to multiply.
   * @return expression.
   */
  public ExpressionBuilder multiply(ExpressionBuilder exp) {
    expression.append(MULTIPLY_SYMBOL);
    expression.append(OPEN_BRACKET);
    expression.append(exp);
    expression.append(CLOSE_BRACKET);
    return this;
  }

  /**
   * Add '(' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder openBracket() {
    expression.append(OPEN_BRACKET);
    return this;
  }

  /**
   * Add ')' symbol to expression.
   * 
   * @return expression.
   */
  public ExpressionBuilder closeBracket() {
    expression.append(CLOSE_BRACKET);
    return this;
  }

  /**
   * Append custom symbol.
   * 
   * @param symbol
   * @return expression.
   */
  public ExpressionBuilder symbol(String symbol) {
    expression.append(symbol);
    return this;
  }

  /**
   * Clear the expression.
   * 
   * @return new expression.
   */
  public ExpressionBuilder clear() {
    expression = new StringBuilder();
    return this;
  }

  /**
   * Returns expression as String object.
   * 
   * @return Text representation of expression.
   */
  public String toString() {
    return expression.toString();
  }
}

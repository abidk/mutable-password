/**
 * Copyright 2011 Abid Khalil
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
 * Allows you to create and parse the mutable block.
 * 
 * @author Abid
 * 
 */
public class MutableBlock {
  /** Mutable block start identifier. */
  public static final char MUTABLE_BLOCK_START_TAG = '[';
  /** Mutable block end identifier. */
  public static final char MUTABLE_BLOCK_END_TAG = ']';
  /** Expression start identifier. */
  public static final char EXPRESSION_START_TAG = '{';
  /** Expression end identifier. */
  public static final char EXPRESSION_END_TAG = '}';

  // password type
  private String type;

  // expression for password
  private String expression;

  /**
   * Takes the password type and the expression as separate entities.
   * 
   * @param type
   * @param expression
   */
  public MutableBlock(String type, String expression) {
    this.type = type;
    this.expression = expression;
  }

  /**
   * Takes the password and separates it into the password type and expression.
   * 
   * @param password
   */
  public MutableBlock(String password) {
    int startTag = password.indexOf(MUTABLE_BLOCK_START_TAG);
    int endTag = password.lastIndexOf(MUTABLE_BLOCK_END_TAG);
    // check to see if we have an mutable block
    if (startTag != -1 && endTag != -1) {
      String mutateBlock = password.substring(startTag + 1, endTag);
      int expressionStartTag = mutateBlock.indexOf(EXPRESSION_START_TAG);
      int expressionEndTag = mutateBlock.lastIndexOf(EXPRESSION_END_TAG);

      if (expressionStartTag != -1 && expressionEndTag != -1) {
        // get the type of password e.g. time
        this.type = mutateBlock.substring(0, expressionStartTag);
        // get the expression e.g. year
        this.expression = mutateBlock.substring(expressionStartTag + 1, expressionEndTag);
      }
    }
  }

  /**
   * Returns the password type from the mutable block.
   * 
   * @return password type
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the expression from the mutable password block.
   * 
   * @return password expression
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Returns the combined password type and expression as the mutable block.
   * 
   * @return mutable block e.g. [time{year+1}]
   */
  public String toString() {
    String mutableBlock = MUTABLE_BLOCK_START_TAG + type + EXPRESSION_START_TAG + expression + EXPRESSION_END_TAG + MUTABLE_BLOCK_END_TAG;
    return mutableBlock;
  }
}
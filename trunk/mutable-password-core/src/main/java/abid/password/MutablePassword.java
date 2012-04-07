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

import abid.password.evaluator.ExpressionEvaluator;
import abid.password.evaluator.JavascriptExpressionEvaluator;
import abid.password.evaluator.EvaluationException;

/**
 * Abstract class to be extended when creating new mutable password types.
 * 
 * @author Abid
 * 
 */
public abstract class MutablePassword extends Password {

  /**
   * Returns the password type.
   * 
   * @return password type
   */
  public abstract String getPasswordType();

  /**
   * Method will return the evaluation of the mutable block.
   * 
   * @return evaluated password
   * @throws EvaluationException
   */
  public abstract String getEvaluatedPassword() throws EvaluationException;

  private ExpressionEvaluator evaluator = new JavascriptExpressionEvaluator();
  private MutableBlock mutableBlock;
  private String text;

  /**
   * Constructs password as a mutable password object.
   * 
   * @param text
   * @param mutableBlock
   */
  public MutablePassword(String text, MutableBlock mutableBlock) {
    super(text + mutableBlock);
    // TODO escape the brackets in the text so it does not conflict with
    // mutableblock tags.
    this.text = text;
    this.mutableBlock = mutableBlock;
  }

  /**
   * Constructs password as a mutable password object.
   * 
   * @param password
   */
  public MutablePassword(String password) {
    super(password);
    int startTag = password.indexOf(MutableBlock.MUTABLE_BLOCK_START_TAG);
    if (startTag != -1) {
      this.text = password.substring(0, startTag);
      this.mutableBlock = new MutableBlock(password);
    }
  }

  /**
   * Returns password static text.
   * 
   * @return static password text
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the password type.
   * 
   * @return password type
   */
  public String getType() {
    return mutableBlock != null ? mutableBlock.getType() : null;
  }

  /**
   * Returns the mutable block expression.
   * 
   * @return password expression
   */
  public String getExpression() {
    return mutableBlock != null ? mutableBlock.getExpression() : null;
  }

  /**
   * Returns the mutable element of the password.
   * 
   * @return mutable block
   */
  public MutableBlock getMutableBlock() {
    return mutableBlock;
  }

  /**
   * Returns current password evaluator.
   * 
   * @return evaluator
   */
  public ExpressionEvaluator getEvaluator() {
    return evaluator;
  }

  /**
   * Set the evaluator to be used when evaluating passwords.
   * 
   * @param evaluator
   */
  public void setEvaluator(ExpressionEvaluator evaluator) {
    this.evaluator = evaluator;
  }
}

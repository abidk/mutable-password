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

import abid.password.evaluator.Evaluator;
import abid.password.evaluator.JavascriptEvaluator;
import abid.password.evaluator.ParseException;

/**
 * Allows this abstract class to be extended to allow the extension of further
 * mutable passwords.
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
   * @throws ParseException
   */
  public abstract String getEvaluatedPassword() throws ParseException;

  private Evaluator evaluator = new JavascriptEvaluator();
  private MutableBlock mutableBlock;
  private String text;

  /**
   * Takes the static password text and mutable block as separate parameters.
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
   * Takes the password, which will get split as the static element and the
   * mutable element.
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
   * Returns the static text of the password.
   * 
   * @return static password text
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the type of mutable password.
   * 
   * @return password type
   */
  public String getType() {
    return mutableBlock != null ? mutableBlock.getType() : null;
  }

  /**
   * Returns the expression of the mutable block.
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
   * Returns the object of the evaluator.
   * 
   * @return evaluator object
   */
  public Evaluator getEvaluator() {
    return evaluator;
  }

  /**
   * Set a new evaluator.
   * 
   * @param evaluator
   */
  public void setEvaluator(Evaluator evaluator) {
    this.evaluator = evaluator;
  }
}
/* Copyright 2009 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package abid.password;

/**
 * Allows this abstract class to be extended to allow the extension of further
 * mutable passwords.
 * 
 * @author Abid
 * 
 */
public abstract class MutablePassword extends Password {

  public abstract String getPasswordType();

  private MutableBlock mutableBlock;

  public MutablePassword(String password) {
    super(password);
    mutableBlock = new MutableBlock(password);
  }

  /**
   * 
   * @return static password text
   */
  public String getText() {
    return mutableBlock.getText();
  }

  /**
   * 
   * @return password type
   */
  public String getType() {
    return mutableBlock.getType();
  }

  /**
   * 
   * @return password expression
   */
  public String getExpression() {
    return mutableBlock.getExpression();
  }
}
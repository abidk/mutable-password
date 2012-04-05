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
package abid.password.types;

import abid.password.MutableBlock;
import abid.password.MutablePasswordBuilder;
import abid.password.StatefulMutablePassword;
import abid.password.parameters.TimeParameter;

/**
 * Convenient builder class to build password.
 * 
 * @author Abid
 * 
 */
public class CaesarCipherPasswordBuilder extends MutablePasswordBuilder {

  /**
   * Builds Caesar Cipher Password.
   */
  public CaesarCipherPasswordBuilder() {
    super(CaesarCipherPassword.PASSWORD_TYPE);
  }

  /**
   * Creates a state based mutable password based on the input values.
   * 
   * @param text
   * @param timeType
   * @return mutable password with state
   */
  public StatefulMutablePassword createPassword(String text, TimeParameter timeType) {
    MutableBlock block = createMutableBlock(timeType);
    return new CaesarCipherPassword(text + block);
  }

  /**
   * Creates a state based mutable password based on the input values.
   * 
   * @param text
   * @return mutable password with state
   */
  public StatefulMutablePassword createPassword(String text) {
    MutableBlock block = createMutableBlock("state");
    return new CaesarCipherPassword(text + block);
  }
}

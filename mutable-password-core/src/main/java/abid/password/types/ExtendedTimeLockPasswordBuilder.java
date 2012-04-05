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
import abid.password.MutablePassword;
import abid.password.MutablePasswordBuilder;
import abid.password.parameters.TimeParameter;

/**
 * Convenient builder class to build password.
 * 
 * @author Abid
 * 
 */
public class ExtendedTimeLockPasswordBuilder extends MutablePasswordBuilder {

  /**
   * Builds Extended Time Lock Password.
   */
  public ExtendedTimeLockPasswordBuilder() {
    super(ExtendedTimeLockPassword.PASSWORD_TYPE);
  }

  /**
   * Create the mutable password based on the input values.
   * 
   * @param text
   * @param extendedTimeValue
   * @param lockTimeType
   * @param lockStartTime
   * @param lockEndTime
   * @return mutable password
   */
  public MutablePassword createPassword(String text, TimeParameter extendedTimeValue, TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    MutableBlock block = createMutableBlock(extendedTimeValue, lockTimeType, lockStartTime, lockEndTime);
    String mutablePassword = text + block;
    return new ExtendedTimeLockPassword(mutablePassword);
  }

}

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

import abid.password.parameters.TimeParameter;

/**
 * 
 * Contains basic building block methods.
 * 
 * @author Abid
 * 
 */
public abstract class MutablePasswordBuilder {

  private String passwordType;

  /**
   * Password type that needs building.
   * 
   * @param passwordType
   */
  public MutablePasswordBuilder(String passwordType) {
    this.passwordType = passwordType;
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param timeValue
   * @return mutable block
   */
  public MutableBlock createMutableBlock(TimeParameter timeValue) {
    return createMutableBlock(timeValue.getTextField());
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param timeType
   * @param start
   * @param end
   * @return mutable block
   */
  public MutableBlock createMutableBlock(TimeParameter timeType, int start, int end) {
    String type = timeType.getTextField();
    String expression = type + ">=" + start + "&&" + type + "<=" + end;
    MutableBlock block = createMutableBlock(expression);
    return block;
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param extendedTimeValue
   * @param lockTimeType
   * @param lockStartTime
   * @param lockEndTime
   * @return mutable block
   */
  public MutableBlock createMutableBlock(TimeParameter extendedTimeValue, TimeParameter lockTimeType, int lockStartTime, int lockEndTime) {
    String extendExpression = extendedTimeValue.getTextField();
    String lockExpression = lockTimeType.getTextField() + ">=" + lockStartTime + "&&" + lockTimeType.getTextField() + "<=" + lockEndTime;
    String expression = extendExpression + "," + lockExpression;
    MutableBlock block = createMutableBlock(expression);
    return block;
  }

  /**
   * Creates a mutable block based on the input values.
   * 
   * @param expression
   * @return mutable block
   */
  public MutableBlock createMutableBlock(String expression) {
    MutableBlock block = new MutableBlock(passwordType, expression);
    return block;
  }

}

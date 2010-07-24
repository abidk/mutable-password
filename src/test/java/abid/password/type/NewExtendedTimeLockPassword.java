/**
 * Copyright 2010 Abid Khalil
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

package abid.password.type;

import abid.password.MutableBlock;
import abid.password.parameters.TimeParameter;
import abid.password.types.ExtendedTimeLockPassword;

public class NewExtendedTimeLockPassword extends ExtendedTimeLockPassword {

  public NewExtendedTimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  public static MutableBlock createMutableBlock(TimeParameter extendedTimeValue, TimeParameter lockTimeType, String lockStartTime, String lockEndTime) {
    String extendExpression = extendedTimeValue.getTextField();
    String lockExpression = lockTimeType.getTextField() + ">=" + lockStartTime + "&&" + lockTimeType.getTextField() + "<=" + lockEndTime;
    String expression = extendExpression + "," + lockExpression;
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

}

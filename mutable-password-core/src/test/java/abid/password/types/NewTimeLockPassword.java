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

package abid.password.types;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.parameters.TimeParameter;
import abid.password.types.TimeLockPassword;

public class NewTimeLockPassword extends TimeLockPassword {

  public NewTimeLockPassword(String text, MutableBlock block) {
    super(text, block);
  }

  public static MutableBlock createMutableBlock(TimeParameter timeType, String start, String end) {
    String type = timeType.getTextField();
    String expression = type + ">=" + start + "&&" + type + "<=" + end;
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, expression);
    return block;
  }

  public static MutablePassword createPassword(String text, TimeParameter timeType, String start, String end) {
    MutableBlock block = createMutableBlock(timeType, start, end);
    return new TimeLockPassword(text, block);
  }
  
}

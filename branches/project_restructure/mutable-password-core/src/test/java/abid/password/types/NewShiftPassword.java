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
import abid.password.types.ShiftPassword;

public class NewShiftPassword extends ShiftPassword {

  public NewShiftPassword(String text, MutableBlock block) {
    super(text, block);

  }

  public static MutableBlock createMutableBlock(String shiftValue) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, shiftValue);
    return block;
  }

  public static MutablePassword createPassword(String text, String shiftValue) {
    MutableBlock block = createMutableBlock(shiftValue);
    return new ShiftPassword(text, block);
  }
}
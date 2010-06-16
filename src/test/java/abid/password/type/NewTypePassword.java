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
import abid.password.MutablePassword;

public class NewTypePassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "test";

  public NewTypePassword(String password) {
    super(password);
  }

  public NewTypePassword(String text, MutableBlock mutableBlock) {
    super(text, mutableBlock);
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    return confirmPassword.equals(getText() + "1");
  }

  public static MutablePassword createPassword(String text) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, 1);
    return new NewTypePassword(text, block);
  }

}
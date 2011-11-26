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
package abid.password.springmvc.validator;

import org.springframework.validation.Errors;

import abid.password.springmvc.model.CaesarCipherPasswordFormBean;
import abid.password.springmvc.model.ExtendedPasswordFormBean;
import abid.password.springmvc.model.ExtendedTimeLockPasswordFormBean;
import abid.password.springmvc.model.SimplePasswordFormBean;
import abid.password.springmvc.model.TimeLockPasswordFormBean;

public interface MutablePasswordValidator {

  public void validateSimplePassword(SimplePasswordFormBean formBean, Errors errors);

  public void validateCaesarCipherPassword(CaesarCipherPasswordFormBean formBean, Errors errors);

  public void validateExtendedPassword(ExtendedPasswordFormBean formBean, Errors errors);

  public void validateExtendedTimeLockPassword(ExtendedTimeLockPasswordFormBean formBean, Errors errors);

  public void validateTimeLockPassword(TimeLockPasswordFormBean formBean, Errors errors);
}
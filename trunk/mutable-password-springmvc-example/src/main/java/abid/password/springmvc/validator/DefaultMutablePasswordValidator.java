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

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import abid.password.springmvc.model.CaesarCipherPasswordFormBean;
import abid.password.springmvc.model.ExtendedPasswordFormBean;
import abid.password.springmvc.model.ExtendedTimeLockPasswordFormBean;
import abid.password.springmvc.model.SimplePasswordFormBean;
import abid.password.springmvc.model.TimeLockPasswordFormBean;
import abid.password.springmvc.util.StringUtils;

@Component
public class DefaultMutablePasswordValidator implements MutablePasswordValidator {

  public void validateSimplePassword(SimplePasswordFormBean formBean, Errors errors) {
    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "user.username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "user.password.required");
    }
  }

  public void validateCaesarCipherPassword(CaesarCipherPasswordFormBean formBean, Errors errors) {
    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "user.username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "user.password.required");
    }
  }

  public void validateExtendedPassword(ExtendedPasswordFormBean formBean, Errors errors) {
    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "user.username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "user.password.required");
    }
  }

  public void validateExtendedTimeLockPassword(ExtendedTimeLockPasswordFormBean formBean, Errors errors) {
    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "user.username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "user.password.required");
    }
  }

  public void validateTimeLockPassword(TimeLockPasswordFormBean formBean, Errors errors) {
    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "user.username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "user.password.required");
    }

  }
}
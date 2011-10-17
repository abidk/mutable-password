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
import org.springframework.validation.Validator;

import abid.password.springmvc.model.SimplePasswordFormBean;
import abid.password.springmvc.util.StringUtils;

public class SimplePasswordValidator implements Validator {

  public boolean supports(Class<?> cls) {
    return SimplePasswordFormBean.class.isAssignableFrom(cls);
  }

  public void validate(Object target, Errors errors) {
    SimplePasswordFormBean formBean = (SimplePasswordFormBean) target;

    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "password.required");
    }
  }

}
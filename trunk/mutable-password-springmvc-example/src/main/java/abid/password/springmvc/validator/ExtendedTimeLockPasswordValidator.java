package abid.password.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import abid.password.springmvc.model.ExtendedTimeLockPasswordFormBean;
import abid.password.springmvc.util.StringUtils;

public class ExtendedTimeLockPasswordValidator implements Validator {

  public boolean supports(Class<?> cls) {
    return ExtendedTimeLockPasswordFormBean.class.isAssignableFrom(cls);
  }

  public void validate(Object target, Errors errors) {
    ExtendedTimeLockPasswordFormBean formBean = (ExtendedTimeLockPasswordFormBean) target;

    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "password.required");
    }
  }

}
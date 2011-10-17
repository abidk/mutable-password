package abid.password.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import abid.password.springmvc.model.TimeLockPasswordFormBean;
import abid.password.springmvc.util.StringUtils;

public class TimeLockPasswordValidator implements Validator {

  public boolean supports(Class<?> cls) {
    return TimeLockPasswordFormBean.class.isAssignableFrom(cls);
  }

  public void validate(Object target, Errors errors) {
    TimeLockPasswordFormBean formBean = (TimeLockPasswordFormBean) target;

    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "password.required");
    }

  }

}
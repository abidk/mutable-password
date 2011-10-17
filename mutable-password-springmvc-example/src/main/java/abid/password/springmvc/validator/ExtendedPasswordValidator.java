package abid.password.springmvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import abid.password.springmvc.model.ExtendedPasswordFormBean;
import abid.password.springmvc.util.StringUtils;

public class ExtendedPasswordValidator implements Validator {

  public boolean supports(Class<?> cls) {
    return ExtendedPasswordFormBean.class.isAssignableFrom(cls);
  }

  public void validate(Object target, Errors errors) {
    ExtendedPasswordFormBean formBean = (ExtendedPasswordFormBean) target;

    if (StringUtils.isEmpty(formBean.getUsername())) {
      errors.rejectValue("username", "username.required");
    }

    if (StringUtils.isEmpty(formBean.getPassword())) {
      errors.rejectValue("password", "password.required");
    }
  }

}
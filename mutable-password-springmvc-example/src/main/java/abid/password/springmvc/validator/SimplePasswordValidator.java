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
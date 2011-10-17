package abid.password.wicket.converters;

import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import abid.password.evaluator.ParseException;
import abid.password.types.ExtendedPassword;

public class ExtendedPasswordConverter implements IConverter<ExtendedPassword> {

  private static final long serialVersionUID = 1L;

  public ExtendedPassword convertToObject(String value, Locale locale) {
    ExtendedPassword password = new ExtendedPassword(value);
    return password;
  }

  public String convertToString(ExtendedPassword value, Locale locale) {
    try {
      return value.getEvaluatedPassword();
    } catch (ParseException e) {
      error("", "converterFailed", e);
    }
    return null;
  }

  private void error(String value, String errorKey, Exception e) {
    ConversionException exception = new ConversionException("Could not convert password into '" + ExtendedPassword.class.getName() + "'", e);
    exception.setSourceValue(value);
    exception.setResourceKey(getClass().getSimpleName() + "." + errorKey);
    throw exception;
  }
}

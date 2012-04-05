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
package abid.password.wicket.converters;

import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import abid.password.evaluator.EvaluationException;
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
    } catch (EvaluationException e) {
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

**If you have come up with a new implementation, please contribute the code.**


---

1. Extend MutablePassword class
```
public class NewPassword extends MutablePassword {
...
}
```

2. Implement evaluation method
```
  @Override
  public String getEvaluatedPassword() throws EvaluationException {
    // add code to evaluate new password
    String evaluatedPassword = getText() + new Date();
    return evaluatedPassword;
  }
```

3. Implement confirm method
```
  @Override
  public boolean confirmPassword(String confirmPassword) throws EvaluationException {
    try {
      String evaluatedPassword = getEvaluatedPassword();
      return confirmPassword.equals(evaluatedPassword);
    } catch (EvaluationException e) {
      throw new PasswordException(e);
    }
  }
```

4. Register the new password type
```
PasswordTypeRegistry.registerPasswordType( NewPassword.class );
```
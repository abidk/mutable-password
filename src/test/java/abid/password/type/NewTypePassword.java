package abid.password.type;

import abid.password.MutableBlock;
import abid.password.MutablePassword;

public class NewTypePassword extends MutablePassword {

  public static final String PASSWORD_TYPE = "test";

  public NewTypePassword(String password) {
    super(password);
  }

  public NewTypePassword(String text, MutableBlock mutableBlock) {
    super(text, mutableBlock);
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    return confirmPassword.equals(getText() + "1");
  }

  public static MutablePassword createPassword(String text) {
    MutableBlock block = new MutableBlock(PASSWORD_TYPE, 1);
    return new NewTypePassword(text, block);
  }

}
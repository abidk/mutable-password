package abid.password.types;

import java.security.MessageDigest;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.util.DigestUtils;

public class HashedPassword extends MutablePassword {

  /** Password type name **/
  public static final String PASSWORD_TYPE = "hashed";

  /**
   * Password is separated into the hashedPassword and mutable block. The
   * mutable block is separated as the password type and expression.
   * 
   * @param password
   */
  public HashedPassword(String password) {
    super(password);
  }

  /**
   * Takes the hashedPassword and mutable block as separate objects. The mutable
   * block is separated as the password type and expression.
   * 
   * @param hashedPassword
   * @param block
   */
  public HashedPassword(String hashedPassword, MutableBlock block) {
    super(hashedPassword, block);
  }

  @Override
  public String getPasswordType() {
    return PASSWORD_TYPE;
  }

  @Override
  public String getEvaluatedPassword() {
    return getText();
  }

  @Override
  public boolean confirmPassword(String confirmPassword) {
    String[] expression = getExpression().split(",");
    String algorithm = expression[0];
    int digestLength = Integer.valueOf(expression[1]);
    byte[] saltHex = expression[2].getBytes();

    byte[] confirmBytes = DigestUtils.hash(confirmPassword.getBytes(), saltHex, algorithm, digestLength);
    return MessageDigest.isEqual(getText().getBytes(), DigestUtils.toHex(confirmBytes).getBytes());
  }

}
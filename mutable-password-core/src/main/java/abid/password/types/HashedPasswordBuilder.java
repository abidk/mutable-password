/**
 * Copyright 2012 Abid Khalil
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
package abid.password.types;

import abid.password.MutableBlock;
import abid.password.MutablePassword;
import abid.password.MutablePasswordBuilder;
import abid.password.util.DigestUtils;

/**
 * Builds hashed passwords.
 * 
 * @author Abid
 * 
 */
public class HashedPasswordBuilder extends MutablePasswordBuilder {

  public static final String DEFAULT_ALGORITHM = "SHA-256";
  public static final int DEFAULT_DIGEST_LENGTH = 32;
  public static final int DEFAULT_SALT_LENGTH = 10;

  private String algorithm = DEFAULT_ALGORITHM;
  private int digestLength = DEFAULT_DIGEST_LENGTH;
  private int saltLength = DEFAULT_SALT_LENGTH;

  /**
   * Builds Hashed Password.
   */
  public HashedPasswordBuilder() {
    super(HashedPassword.PASSWORD_TYPE);
  }

  /**
   * Change the algorithm used to hash the password.
   * 
   * Default: SHA-256
   * 
   * @param algorithm
   */
  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Specify the salt length. The longer the better.
   * 
   * Default: 10
   * 
   * @param saltLength
   */
  public void setSaltLength(int saltLength) {
    this.saltLength = saltLength;
  }

  /**
   * Specify digest length. The longer the better.
   * 
   * Default: 32
   * 
   * @param digestLength
   */
  public void setDigestLength(int digestLength) {
    this.digestLength = digestLength;
  }

  /**
   * Will create a hashed password using a generated salt. Uses specified salt
   * length, digest length and algorithm to produce a hash.
   * 
   * @param plainPassword
   * @param salt
   * @return mutable password
   */
  public MutablePassword createPassword(String plainPassword) {
    return createPassword(plainPassword, DigestUtils.generateSalt(saltLength));
  }

  /**
   * Will create a hashed password using a specified salt. Uses specified digest
   * length and algorithm to produce a hash.
   * 
   * @param plainPassword
   * @param salt
   * @return mutable password
   */
  public MutablePassword createPassword(String plainPassword, byte[] salt) {
    String hexSalt = DigestUtils.toHex(salt);
    byte[] hashedPassword = DigestUtils.hash(plainPassword.getBytes(), hexSalt.getBytes(), algorithm, digestLength);

    String expression = algorithm + "," + digestLength + "," + hexSalt;
    MutableBlock block = createMutableBlock(expression);
    return new HashedPassword(DigestUtils.toHex(hashedPassword), block);
  }

}

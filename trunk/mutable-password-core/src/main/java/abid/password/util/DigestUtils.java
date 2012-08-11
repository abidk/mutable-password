package abid.password.util;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class DigestUtils {

  private DigestUtils() {
  }

  public static byte[] hash(byte[] input, byte[] salt, String algorithm, int digestLength) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
      messageDigest.update(input);
      messageDigest.update(salt);

      byte[] raw = new byte[digestLength];
      messageDigest.digest(raw, 0, digestLength);
      return raw;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Could not hash input with algorithm '" + algorithm + "'.", e);
    } catch (DigestException e) {
      throw new RuntimeException("Could not digest input because...", e);
    }
  }

  public static byte[] generateSalt(int length) {
    Random rnd = new Random();
    byte[] salt = new byte[length];
    rnd.nextBytes(salt);
    return salt;
  }

  public static String toHex(byte[] input) {
    StringBuffer hexString = new StringBuffer();
    for (byte b : input) {
      // mask byte so it produces correct results for negative values.
      hexString.append(Integer.toHexString(0xFF & b));
    }
    return hexString.toString();
  }
}

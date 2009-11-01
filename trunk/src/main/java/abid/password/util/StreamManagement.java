package abid.password.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamManagement {

  public static String convertStreamToString(InputStream is) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        // ignore
      }
    }

    return sb.toString();
  }
}

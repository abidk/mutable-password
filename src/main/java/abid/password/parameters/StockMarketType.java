package abid.password.parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import abid.password.util.StreamManagement;

/**
 * This is an example of the types of parameters that can be used to change your
 * passwords.
 * 
 * Please be advised this could stop working at any time and is just an
 * experimental class. Use at your own risk!
 * 
 * 
 * @author Abid
 * 
 */
public enum StockMarketType {

  FTSE100("ftse100", "?s=%5EFTSE&f=sl1d1t1c1ohgv&e=.csv"), DOW("dow", "?s=%5EDJI&f=sl1d1t1c1ohgv&e=.csv");

  // hmm, use Yahoo to get stock data
  public static final String YAHOO_FINANCE_URL = "http://uk.old.finance.yahoo.com/d/quotes.csv";

  private String market;
  private String url;

  private StockMarketType(String market, String url) {
    this.market = market;
    this.url = url;
  }

  public String getMarket() {
    return market;
  }

  public Integer getIndexValue() {
    try {
      URL dataUrl = new URL(YAHOO_FINANCE_URL + url);
      URLConnection connection = dataUrl.openConnection();
      InputStream inputStream = connection.getInputStream();

      String csvData = StreamManagement.convertStreamToString(inputStream);
      String[] splitData = csvData.split(",");

      //TODO fix the index value being converted into a integer.
      Integer value = Integer.valueOf(splitData[1]);
      return value;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public static Map<String, Integer> getValues() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (StockMarketType stock : StockMarketType.values()) {
      map.put(stock.getMarket(), stock.getIndexValue());
    }
    return map;
  }

}

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

package abid.password.parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import abid.password.util.StringUtils;

/**
 * This is an example of the types of parameters that can be used to change your
 * passwords.
 * 
 * Please be advised this could stop working at any time and is just an
 * experimental class. Use it at your own risk!
 * 
 * @author Abid
 */
public enum StockMarketParameter {

  /** FTSE 100 market and query URL. */
  FTSE100("ftse100", "?s=%5EFTSE&f=sl1d1t1c1ohgv&e=.csv"),
  /** DOW market and query URL. */
  DOW("dow", "?s=DOW&f=sl1d1t1c1ohgv&e=.csv");

  /** URL to Yahoo's finance API. */
  public static final String YAHOO_FINANCE_URL = "http://download.finance.yahoo.com/d/quotes.csv";

  private String market;
  private String query;

  private StockMarketParameter(String market, String query) {
    this.market = market;
    this.query = query;
  }

  public String getMarket() {
    return market;
  }

  /**
   * A request is carried out to the Yahoo service and returns the index value.
   * We may get an error either from requesting or parsing the response, so
   * we're throwing the exception.
   * 
   * @return index value
   * @throws IOException
   */
  public Number getIndexValue() throws IOException {
    URL dataUrl = new URL(YAHOO_FINANCE_URL + query);
    URLConnection connection = dataUrl.openConnection();
    InputStream inputStream = connection.getInputStream();
    String csvData = StringUtils.convertStreamToString(inputStream);
    inputStream.close();
    String[] splitData = csvData.split(",");

    // hmm do i want to make it a float, maybe it's best to convert it into an
    // integer and lose the accuracy
    Float value = Float.valueOf(splitData[1]);
    return value;
  }

  /**
   * Loops through the enum values and creates a map of all the parameters.
   * 
   * @return market parameters
   * @throws IOException
   */
  public static Map<String, Parameter> getParameters() throws IOException {
    Map<String, Parameter> map = new HashMap<String, Parameter>();
    for (StockMarketParameter stock : StockMarketParameter.values()) {
      map.put(stock.getMarket(), new Parameter(stock.getIndexValue()));

    }
    return map;
  }

  @Override
  public String toString() {
    return market;
  }
}

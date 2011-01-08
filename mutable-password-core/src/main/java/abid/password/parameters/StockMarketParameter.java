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

import abid.password.util.StreamUtils;

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
public enum StockMarketParameter {

  FTSE100("ftse100", "?s=%5EFTSE&f=sl1d1t1c1ohgv&e=.csv"), DOW("dow", "?s=%5EDJI&f=sl1d1t1c1ohgv&e=.csv");

  // hmm, use Yahoo to get stock data
  public static final String YAHOO_FINANCE_URL = "http://uk.old.finance.yahoo.com/d/quotes.csv";

  private String market;
  private String url;

  private StockMarketParameter(String market, String url) {
    this.market = market;
    this.url = url;
  }

  public String getMarket() {
    return market;
  }

  public Number getIndexValue() throws IOException{
    URL dataUrl = new URL(YAHOO_FINANCE_URL + url);
    URLConnection connection = dataUrl.openConnection();
    InputStream inputStream = connection.getInputStream();

    String csvData = StreamUtils.convertStreamToString(inputStream);
    try {
      inputStream.close();
    } catch (IOException e) {
      
    }

    String[] splitData = csvData.split(",");

    // hmm do i want to make it a float, maybe it's best to convert it into an
    // integer and lose the accuracy
    Float value = Float.valueOf(splitData[1]);
    return value;

  }

  public static Map<String, Number> getValues() throws IOException {
    Map<String, Number> map = new HashMap<String, Number>();
    for (StockMarketParameter stock : StockMarketParameter.values()) {
      map.put(stock.getMarket(), stock.getIndexValue());
    }
    return map;
  }

}
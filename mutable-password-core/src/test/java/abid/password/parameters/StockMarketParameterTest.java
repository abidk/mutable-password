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
package abid.password.parameters;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class StockMarketParameterTest {

  @Test
  public void getMarketShouldReturnMarketName() {
    StockMarketParameter marketParameter = StockMarketParameter.DOW;
    assertEquals("dow", marketParameter.getMarket());

    marketParameter = StockMarketParameter.FTSE100;
    assertEquals("ftse100", marketParameter.getMarket());
  }

  @Test
  public void indexValueShouldReturnMarketIndex() {
    try {
      StockMarketParameter marketParameter = StockMarketParameter.DOW;
      assertNotNull(marketParameter.getIndexValue());
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }

    try {
      StockMarketParameter ftseMarketParameter = StockMarketParameter.FTSE100;
      assertNotNull(ftseMarketParameter.getIndexValue());
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }
  }

  @Test
  public void getParametersShouldReturnStockMarketParameters() {
    try {
      Map<String, Parameter> marketValues = StockMarketParameter.getParameters();
      assertNotNull(marketValues);
      for (StockMarketParameter marketParameter : StockMarketParameter.values()) {
        marketValues.containsKey(marketParameter.getMarket());
      }
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }
  }

  @Test
  public void toStringShouldReturnMarketName() {
    assertEquals("ftse100", StockMarketParameter.FTSE100.toString());
    assertEquals("dow", StockMarketParameter.DOW.toString());
  }
}

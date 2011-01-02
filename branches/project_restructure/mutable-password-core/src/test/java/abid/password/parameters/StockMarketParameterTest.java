/**
 * Copyright 2010 Abid Khalil
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
import java.util.Map;

import junit.framework.TestCase;

public class StockMarketParameterTest extends TestCase {

  public void testStockMarketName() {
    StockMarketParameter marketParameter = StockMarketParameter.DOW;
    assertEquals("dow", marketParameter.getMarket());

    StockMarketParameter ftseMarketParameter = StockMarketParameter.FTSE100;
    assertEquals("ftse100", ftseMarketParameter.getMarket());
  }

  public void testStockMarketIndexValue() {
    StockMarketParameter marketParameter = StockMarketParameter.DOW;
    try {
      marketParameter.getIndexValue();
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }

    StockMarketParameter ftseMarketParameter = StockMarketParameter.FTSE100;
    try {
      ftseMarketParameter.getIndexValue();
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }
  }

  public void testStockMarkerParameterValues() {
    try {
      Map<String, Number> marketValues = StockMarketParameter.getValues();
      assertNotNull(marketValues);
      for (StockMarketParameter marketParameter : StockMarketParameter.values()) {
        marketValues.containsKey(marketParameter.getMarket());
      }
    } catch (IOException e) {
      // this might occur because it cannot connect to yahoo
    }
  }
}

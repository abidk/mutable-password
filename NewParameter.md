**If you come up with any other parameters, please feel free to contribute your code.**


---

1. Retrieves the stock market value from Yahoo
```
public enum StockMarketParameter {

  FTSE100("ftse100", "?s=%5EFTSE&f=sl1d1t1c1ohgv&e=.csv"), 
  DOW("dow", "?s=%5EDJI&f=sl1d1t1c1ohgv&e=.csv");

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
    inputStream.close();
    String[] splitData = csvData.split(",");
    Float value = Float.valueOf(splitData[1]);
    return value;
  }
}
```

2. Register the new parameter
```
  for (StockMarketParameter stock : StockMarketParameter.values()) {
    ParameterRegistery.registerParameter(stock.getMarket(), new Parameter(stock.getIndexValue()));
  }
```
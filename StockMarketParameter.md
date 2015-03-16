Parameter Values:
  * dow
  * ftse100

Usage:
```
   StockMarketParameter stock = StockMarketParameter.FTSE100; 
   Password password = new ExtendedPasswordBuilder().createPassword("pass", stock.getMarket());
```
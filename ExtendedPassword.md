Extends your static password to include a parameter which changes.

**Create a password:**<br />
```
// create extended password
Password password = new ExtendedPasswordBuilder().createPassword("passwordText", TimeType.YEAR);

//Save password in database
String storePass = password.getPassword();
```

**Confirm password:**<br />
```
// get database password and confirm it matches
Password password = new MutablePasswordParser().parse("dbPassword");
boolean result = password.confirmPassword("confirmPassword");
```

**Usage:**
```
// This will extend the password based on the hour. So if the time is 13:25, it will add the hour to the password 'abid' and the end result will be 'abid13'.
Password password = new ExtendedPasswordBuilder().createPassword("abid", TimeType.HOUR);
```



```
// This will extend the password based on the hour. So if the year is 2009, it will add the year to the password 'abid' and the end result will be 'abid2009'.
Password password = new ExtendedPasswordBuilder().createPassword("abid", TimeType.YEAR);
```



```
//If the year is 2009 and the month is 4, the password will be year added to the month and appended to the password 'abid' and the end result will be 'abid2013'.
// So, the following month the password will change to 'abid2014'.
Password password = new ExtendedPasswordbuilder().createPassword("abid", "year+month");
```
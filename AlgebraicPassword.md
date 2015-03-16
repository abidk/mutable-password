**Example 1 - Create an algorithm that changes yearly**
```
String expression = "((year+year)*2)-10";
MutablePassword password = new ExtendedPasswordBuilder().createPassword("pAss", expression);

// If the year is 2011, then the result will be 'pAss8034'
password.getEvaluatedPassword();
```

**Example 2 - Create an algorithm that changes monthly (Using Zodiac)**
```
String expression= "(year-10)+zodiac";
MutablePassword password = new ExtendedPasswordBuilder().createPassword("pAss", expression);

// If the date is Jan 2011 then the result will be 'pAss2001Aquarius'
// Next month the password changes to 'pAss2001Pisces' and following year it will be 'pAss2002Aquarius'
password.getEvaluatedPassword();
```


**Example 3 - Create an algorithm that changes every hour**
```
String x = TimeParameter.YEAR.getTextField();
String y = TimeParameter.HOUR.getTextField();
String expression = x + "-" + y;
MutablePassword password = new ExtendedPasswordBuilder().createPassword("pAss", expression);

// If the year is 2011 and the time is 19:44 then the result will be 'pAss1992'
// the next hour the result will be 'pAss1991'
password.getEvaluatedPassword();
```
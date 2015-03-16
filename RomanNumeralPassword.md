**Create password:**
```
MutablePassword password = new RomanNumeralPasswordBuilder().createPassword("pass", TimeParameter.YEAR);

// or

MutablePassword password = new RomanNumeralPasswordBuilder().createPassword("pass", 786);
```

**Confirm password:**
```
Password password = new MutablePasswordParser().parse("dbPassword");
boolean result = password.confirmPassword("confirmPassword");
```

**Notes:**
```
//If you create the password below, the password will become 'passCD'.
RomanNumeralPassword.createPassword("pass", 400);
```
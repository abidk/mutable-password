**Create a password:**
```
// create mutable password 
Password password = new CaesarCipherPasswordBuilder().createPassword("abid", 1);

// save password in database
String storePass = password.getPassword();
```

**Confirm password:**
```
// automatically work out the type of mutable password
Password password = new MutablePasswordParser().parse("dbPassword");

// or manually create password
CaesarCipherPassword password = new CaesarCipherPassword("dbPassword");

// confirm password is correct
boolean result = password.confirmPassword("confirmPassword");
```

**Usage:**
```
// This will shift the password by the hour. So, if the time is 4:25, it will shift the password 'abid' by four characters and the end result will be 'demh'.
Password password = new CaesarCipherPasswordBuilder().createPassword("abid", TimeType.HOUR);
```
**Create a password:**
```
// create shift mutable password 
Password password1 = ShiftPassword.createPassword("abid", 1);
// or
MutablePassword password2 = ShiftPassword.createPassword("abid", 1);
// or
ShiftPassword password3 = ShiftPassword.createPassword("abid", 1);

// save password in database
String storePass = password1.getPassword();
```

**Confirm password:**
```
// get password from database
String dbPassword = ...;

// automatically work out the type of mutable password
Password dynamicPassword = PasswordFactory.getInstance(dbPassword);
// or manually create password
ShiftPassword shiftPassword = new ShiftPassword(dbPassword);

// confirm password is correct
boolean result = dynamicPassword.confirmPassword("confirmPassword");
```

**Usage:**
```
// This will shift the password by the hour. So, if the time is 4:25, it will shift the password 'abid' by four characters and the end result will be 'demh'.
Password password1 = ShiftPassword.createPassword("abid", TimeType.HOUR);
```
**Create a password:**
```
Password password = new TimeLockPasswordBuilder().createPassword("abid", TimeType.HOUR, 0, 24);

// save password in database
String storePass = password.getPassword();
```

**Confirm password:**
```
// get password from the database
Password password = new MutablePasswordParser().parse("dbPassword");
boolean result = password.confirmPassword("confirmPassword");
```

**Example 1:**
```
// This only allows passwords to work between the hours of 1am to 6am.
Password password = new TimeLockPasswordBuilder().createPassword("abid", TimeType.HOUR, 1, 6);
```

**Example 2:**
```
// This will only allow passwords to work between the minutes of 15 and 30.
Password password = new TimeLockPasswordBuilder().createPassword("abid", TimeType.MINUTE, 15, 45);
```

**Example 3:**
```
// This only allows passwords to work between and equal to Monday and Thursday.
Password password = new TimeLockPasswordBuilder().createPassword("dayOfWeek", TimeType.DAY_OF_WEEK, Calendar.MONDAY, Calendar.THURSDAY);

// Please note:
//Calendar.SUNDAY = 1
//Calendar.MONDAY = 2
//Calendar.TUESDAY = 3
//Calendar.WEDNESDAY = 4
//Calendar.THURSDAY = 5
//Calendar.FRIDAY = 6
//Calendar.SATURDAY = 7
```
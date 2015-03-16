
```
// get password from database
Password password = new MutablePasswordParser().parse("dbPassword");

// confirm password
boolean result = password.confirmPassword("confirmPassword");
```
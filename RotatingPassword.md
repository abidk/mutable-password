**Create password:**
```
Password password = new RotatingPasswordBuilder().createPassword("pass", "123456");

// store in database
String pass = password.getPassword();
```

**Confirm password:**
```
StatefulMutablePassword password = (StatefulMutablePassword) new MutablePasswordParser().parse("dbPassword");

// get state from database/filestore
int dbState = ...;
password.setState(dbState);

// now confirm password is correct
boolean result = password.confirmPassword("confirmPassword");
```

**Notes:**
  * It is important that you set the state, otherwise the password will never rotate.
  * You can change the state at every successful login.

The state rotates the password e.g. If the following password is created
`RotatingPassword.createPassword("pass", "74F56");`

The results are:
| **State** | **Actual Password** |
|:----------|:--------------------|
| 0 | pass7 |
| 1 | pass4 |
| 2 | passF |
| 3 | pass5 |
| 4 | pass6 |
| 5 | pass7 (Rotates) |
| 6 | pass4 (Rotates) |
| 7 | passF (Rotates) |
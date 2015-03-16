Securely store password by hashing and salting plain password.

**Create a password:**
```
// using generated salt
Password hashedPassword = new HashedPasswordBuilder().createPassword("plainPassword");

// or specify custom salt:
Password hashedPassword = new HashedPasswordBuilder().createPassword("plainPassword", "salt".getBytes());

//Save password in database
String storePass = password.getPassword();
```

**Confirm password:**
```
// get database password and confirm it matches
Password password = new MutablePasswordParser().parse("dbPassword");
boolean result = password.confirmPassword("confirmPassword");
```


**Change the hashing algorithm:**
```
HashedPasswordBuilder builder = new HashedPasswordBuilder();
builder.setAlgorithm("MD5");
builder.setDigestLength(16);
builder.setSaltLength(1);

Password hashedPassword = builder.createPassword("plainPassword");
```
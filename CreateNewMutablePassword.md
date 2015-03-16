I have included a test example in the source code, which shows you how to create a new mutable password.


1. Create a class and extend the abstract class MutablePassword

```
Public class NewPassword extends MutablePassword {

// implement methods
}
```


2. Inject the new mutable password implementation into the factory

```
PasswordFactory.add( NewPassword.class );
```

3. Create and store the password using your new implementation.

4. Load and confirm you password like so:

```
// You can then just confirm normally
Password password = PasswordFactory.getInstance("SomePasswordFromDB");
password.confirmPassword( "confirmPassword" )
```
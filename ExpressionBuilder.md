
```
ExpressionBuilder builder = new ExpressionBuilder();
builder.value("year").greaterEquals().value(1).and().value("year").lessEquals().value(2);

// i.e. "year>=1&&year<=2"
String expression = builder.toString()
```

Check the [Test Case](https://mutable-password.googlecode.com/svn/trunk/mutable-password-core/src/test/java/abid/password/ExpressionBuilderTest.java) for more examples.
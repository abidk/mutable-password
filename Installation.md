Using maven you need to

Add the repository:
```
<repositories>
  <repository>
    <id>mutable-password-release</id>
    <name>Mutable password repository</name>
    <url>http://mutable-password.googlecode.com/svn/maven2/releases/</url>
  </repository>
</repositories>
```

Add the dependency:
```
<dependency>
  <groupId>abid.password</groupId>
  <artifactId>mutable-password-core</artifactId>
  <version>1.10</version>
</dependency>
```

If you are using Ant then you can download the library from the repository and include that in your project.
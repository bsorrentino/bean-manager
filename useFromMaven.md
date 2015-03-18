## Getting the Asset from Maven repository ##

Getting the asset is simple. You just need to add a new Repository to your POM. To have a complete list of valid repository see [Issue 32](https://code.google.com/p/bean-manager/issues/detail?id=32)

### Configure Repository within POM ###
```
<repositories>

 
    <!-- IF YOU WANT STAY TUNED ON UPDATE -->
 
    <repository>
  		<id>sonatype</id>
  		<url>https://oss.sonatype.org/content/repositories/snapshots</url>
		<releases>
			<enabled>false</enabled>
		</releases>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
    </repository>
    
</repositories>
```

## Import dependency ##

To use beanmanager-core have to include in **dependencies** section of your POM the following declaration:

### Releases ###

```
    <dependency>
      <groupId>org.bsc.bean</groupId>
      <artifactId>beanmanager-core</artifactId>
      <version>1.4.1</version>
    </dependency>

```

To use JPA extension


```
    <dependency>
      <groupId>org.bsc.bean</groupId>
      <artifactId>beanmanager-jpa</artifactId>
      <version>1.4.1</version>
    </dependency>

```


### Snaphots (If you want stay tuned on updates) ###

```
    <dependency>
      <groupId>org.bsc.bean</groupId>
      <artifactId>beanmanager-core</artifactId>
      <version>1.4.2-SNAPSHOT</version>
    </dependency>
```

To use JPA extension

```
    <dependency>
      <groupId>org.bsc.bean</groupId>
      <artifactId>beanmanager-jpa</artifactId>
      <version>1.4.2-SNAPSHOT</version>
    </dependency>

```
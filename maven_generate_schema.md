# Introduction #



# An Example #

Create a maven profile to generate schema

```
<profile>
 <id>generate-schema</id>
				
	
<dependencies>
  <dependency>
   <groupId>org.bsc.bean</groupId>
   <artifactId>beanmanager-ddlutils</artifactId>
   <version>1.4.1-SNAPSHOT</version>
   <scope>test</scope>
  </dependency>
		
</dependencies>

<build>
  <plugins>
   <plugin>
    <groupId>org.bsc.maven</groupId>
    <artifactId>maven-processor-plugin</artifactId>
    <version>2.0.4</version>
     <executions>
     <execution>
      <id>process</id>
      <goals>
      <goal>process-test</goal>
      </goals>
      <phase>test</phase>
<configuration>
<outputDirectory>${project.build.directory}</outputDirectory>

<processors>
<processor>org.bsc.bean.ddl.processor.JPAProcessor</processor>
</processors>
								
<options>
<driver>org.apache.derby.jdbc.EmbeddedDriver</driver>
<connectionUrl>jdbc:derby</connectionUrl>
</options>

</configuration>


</execution>
</executions>
</plugin>
</plugins>
</build>
</profile>

```
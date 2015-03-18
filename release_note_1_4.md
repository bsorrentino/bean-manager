#1.4 Release notes

## JPA Support ##


The major feature of release 1.4 is the possibility to use Java Persistent Api (JPA) approach to define mapping. To use it, you could add the following dependency to your pom

```

<dependency>
  <groupId>org.bsc.framework</groupId>
  <artifactId>beanmanager-jpa</artifactId>
  <version>1.4</version>
</dependency>
```

To have a list of supported JPA annotations goto [here](JPA_SUPPORTED_ANNOTATIONS.md)

## General Fix ##

Since this release has been tested on a real (big) project, we have done several fix & adjustment  during this period, keeping usage compatibility with previous release 1.3.x

The most important update has consisted in:
> All columns are considered optional during query operation. This means that no error is raised if a bean property is not present in resultset.

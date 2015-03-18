## Introduction ##
In the current release _**1.3.1-SNAPSHOT**_ (i.e. under development) we have introduced a new feature to auto generate Primary Key value.

To define an user function to generate PK value we have to implement interface **org.bsc.bean.ValueGenerator**, below the interface's definition

```
public interface ValueGenerator<T> {

    T generate( java.sql.Connection c, PropertyDescriptorField pd );
}

```

The framework provide a useful implementation that generate a default **UUID** called **org.bsc.bean.generators.UUIDValueGenerator**

To use it is enough to call a new method **setValueGenerator** available on 
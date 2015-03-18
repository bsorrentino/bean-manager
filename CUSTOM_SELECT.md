#HOW TO USE CUSTOM SELECT COMMAND

# Introduction #

This article describes how to  use custom SQL SELECT command


## Define a Custom Command ##

The easier way to define a custom command is within BeanInfo adding it to BeanDescriptorEntity using the setCustomFindCommand method. An example Is shown below

```

// Snippet from bean info
public class MyBean extends AbstractManagedBeanInfo<MyBean> {

    
    public MyBean() {
        super( MyBean.class );
    } 
    
    public BeanDescriptor getBeanDescriptor() {

        BeanDescriptorEntity entity = new BeanDescriptorEntity(getBeanClass(), "MYTABLE");

        entity.setCustomFindCommand(  "find100", "SELECT {0} FROM {1} WHERE {3} LIMIT 100" );

        return entity;
    }

}

// In the application

PreparedStatement ps = myBeanManager.prepareCustomFind(  connection, "find100" /* command key */,  "" /* WHERE CLAUSE */ );

myBeanManager.find( ps, result );


```

Since Release **1.3.1** you can pass directly custom command to prepareCustomFind method

```

PreparedStatement ps = myBeanManager.prepareCustomFind(  connection, "SELECT {0} FROM {1} WHERE {3} LIMIT 100" ,  "" /* WHERE CLAUSE */ );

myBeanManager.find( ps, result );

```

## CUSTOM COMMAND GUIDELINE ##

To Write custom SQL SELECT command you can use the BeanManager build-in parameters (like MessageFormat  parameters)  listed below:

| **{0}** | list of parameter | mandatory |
|:--------|:------------------|:----------|
| **{1}** | entity name | mandatory |
| **{2}** | join conditions ( contains SQL JOIN clause too ) | optional |
| **{3}** | where condition | optional |


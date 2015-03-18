## Introduction ##
In the current release _**1.3-SNAPSHOT**_ (i.e. under development) we have introduced support of **SQL IN CLAUSE**.
Below there is a snippet of code extract from Unit Test

```
	Customer bean = new Customer();

        for( int i=0; i<100 ; ++i ) {
            bean.setCustomerId(1000 + i);
            bean.setFirstName("name" + i);
            bean.setLastName("sname" + i);
            bean.setAccountId( i );
            bean.setVip(true);
            bean.setNote( "Note" + i );
            bean.setBirthDate( new java.util.Date() );
            customerManager.create(conn, bean);
        }

        List<String> names = Arrays.asList( "name5", "name6", "name7");

        List<Customer> result  = new ArrayList<Customer>( names.size());

        customerManager.find(conn, result, String.format( "${firstName} IN %s order by #{firstName}", BeanManagerUtils.IN(names) ), names);

        assertTrue( result.size()==3 );

        assertEquals( "name5", result.get(0).getFirstName());
        assertEquals( "name6", result.get(1).getFirstName());
        assertEquals( "name7", result.get(2).getFirstName());

```

### Utility Method ###

In class org.bsc.bean.BeanManagerUtils we have add the following method(s)

```

/**
  * Utility function that generate IN clause parameters - i.e.  (?,?,?, .... )
  *
  * Usage.:
  *
  *  Collection<String> params = Arrays.AsList( {"P1", "P2", "P3" } );
  *
  *  manager.find( conn, result, String.format( "${field} IN %s ", IN(params) ), params );
  *
  * @param parameters parameters collection
  * @return IN parameters
  */
 public static String IN( Collection<?> parameters );


```
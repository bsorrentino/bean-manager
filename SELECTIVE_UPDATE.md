# Introduction #

Usually in our program we need to perform 'selective update' (i.e. update only predefined fields). The Bean Manager provides the following methods to perform update:

```
  /**
   * update bean into db ( perform SQL UPDATE )
   *
   *@param conn database connection
   *@param bean object to update into db
   *@exception SQLException
   */
  public int store(Connection conn, T bean) throws SQLException;

  /**
  * update bean into db having the possibility of
  * include/exclude properties into update command
  *
  * <pre>
  * Ex.:
  * int result = manager.store( conn, myBean, StoreConstraints.INCLUDE_PROPERTIES, "prop1",  "prop2, ..." ); // include
  * OR
  * int result = manager.store( conn, myBean, StoreConstraints.EXCLUDE_PROPERTIES, "prop1", "prop2", ...  ); // exclude
  * </pre>
  * @param conn database connection
  * @param bean object to update into db
  * @param properties properties to include/exclude to update
  * @param constraints allow to include or exclude properties from update
  * @exception SQLException
  */
  public int store( Connection conn, T bean, StoreConstraints constraints, String... properties ) throws SQLException ;

  /**
   * update all rows using the property's values of the given bean having the possibility of
   * include/exclude properties into update command
   * 
   * @param conn
   * @param bean
   * @param constraints
   * @param properties
   * @return
   * @throws java.sql.SQLException
   */
  public int storeAll( Connection conn, T bean, StoreConstraints constraints, String... properties) throws SQLException;
```

As you see the 1st **store** method allow to update an entire record with the bean's properties while the 2nd one allow to perform a "**selective update**". Using the StoreConstraints enum (see below) you could specify the fields that you want include or exclude in update operation.


```
public enum StoreConstraints {

    INCLUDE_PROPERTIES,
    EXCLUDE_PROPERTIES
}
```

Below there are some examples from unit test

```
	void updateCustomerInclude( String id, String firstName ) throws SQLException {
		
		Customer bean = new Customer();
		
		bean.setCustomerId(id);
		bean.setFirstName(firstName);
		
		customerManager.store(conn, bean, StoreConstraints.INCLUDE_PROPERTIES, "firstName");
		
		bean = customerManager.findById(conn, id);
		
		Assert.assertNotNull("Customer retreived is null", bean );
		Assert.assertEquals( "Customer.id doesn't match", bean.getCustomerId(), id );
		Assert.assertEquals( "Customer.firstName doesn't match", bean.getFirstName(), firstName );
		
		
	}

	void updateCustomernExclude( String id ) throws SQLException {
		
		Customer bean = new Customer();
		
		bean.setCustomerId(id);
		bean.setLastName("sorrentino");
		
		customerManager.store(conn, bean, StoreConstraints.EXCLUDE_PROPERTIES, "firstName");
		
		bean = customerManager.findById(conn, id);
		
		Assert.assertNotNull("Customer retreived is null", bean );
		Assert.assertEquals( "Customer.id doesn't match", bean.getCustomerId(), id );
		Assert.assertEquals( "Customer.lastName doesn't match", bean.getLastName(), "sorrentino" );
		
		
	}

	void updateCustomer( String id,  String firstName, String lastName ) throws SQLException {
		
		Customer bean = customerManager.findById(conn, id);
		
		Assert.assertNotNull("Customer retreived is null", bean );
		Assert.assertEquals( "Customer.id doesn't match", bean.getCustomerId(), id );

		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		customerManager.store(conn, bean);
		
		bean = customerManager.findById(conn, id);
		
		Assert.assertNotNull("Customer retreived is null", bean );
		Assert.assertEquals( "Customer.id doesn't match", bean.getCustomerId(), id );
		Assert.assertEquals( "Customer.firstName doesn't match", bean.getFirstName(), firstName );
		Assert.assertEquals( "Customer.lastName doesn't match", bean.getLastName(), lastName );
		
		
	}

```
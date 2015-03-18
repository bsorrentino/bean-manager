**This example has been tested on Derby 10.3**

### CREATE TABLE ###

```
CREATE TABLE CUSTOMER (
	FIRST_NAME VARCHAR(30) NOT NULL,
	LAST_NAME VARCHAR(30) NOT NULL,
	ID INTEGER NOT NULL CONSTRAINT EMP_NO_PK PRIMARY KEY,
	ACCOUNT_ID INTEGER NOT NULL 
	);

```

### BEAN ###

```
public class Customer {

	private String firstName;
	private String lastName;
	private int id;
	private int accountId;
	
        @Override
        public String toString() {
            return new StringBuilder(100)
                    .append("customerId=").append(id)
                    .append(";firstName=").append(firstName)
                    .append(";lastName=").append(lastName)
                    .toString();
        }

	public final String getFirstName() {
		return firstName;
	}
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public final String getLastName() {
		return lastName;
	}
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public final int getCustomerId() {
		return id;
	}
	public final void setCustomerId(int id) {
		this.id = id;
	}

        public final int getAccountId() {
		return accountId;
	}
	public final void setAccountId(int value) {
		this.accountId = value;
	}
}

```

### BEANINFO (METADATA) ###

```
public class CustomerBeanInfo extends AbstractManagedBeanInfo<Customer> {


public CustomerBeanInfo() {
 super(Customer.class);
}

public BeanDescriptor getBeanDescriptor() {
 return new BeanDescriptorEntity(getBeanClass(), "CUSTOMER");
}

public PropertyDescriptor[] getPropertyDescriptors() {
try {
 return new PropertyDescriptor[] {
				
  new PropertyDescriptorPK("id", getBeanClass(), "getCustomerId", "setCustomerId" ),
  new PropertyDescriptorField("firstName", getBeanClass(), "getFirstName", "setFirstName" )
	.setFieldName("FIRST_NAME"),
  new PropertyDescriptorField("lastName", getBeanClass(), "getLastName", "setLastName" )
	.setFieldName("LAST_NAME"),
  new PropertyDescriptorField("account_id", getBeanClass(), "getAccountId", "setAccountId" )
       .setSQLType(Types.INTEGER)
 };
} catch (IntrospectionException e) {
	e.printStackTrace();			
}
		
 return EMPTY_PROPERTYDESCRIPTOR;
}
	
}

```


### CRUD OPERATIONS ###

```

BeanManagerFactory factory = BeanManagerFactory.getFactory();

//
// Create a beanManager for bean Customer
//                
customerManager = (BeanManager<Customer>)factory.createBeanManager(Customer.class);

//
// Create a Customer //
Account account = createAccount();
                
Customer bean = new Customer();
		
bean.setCustomerId(1);
bean.setFirstName("name1");
bean.setLastName("sname1");
bean.setAccountId( account.getAccountId() );
	
// conn is a std jdbc connection	
customerManager.create(conn, bean); // Insert customer on db

//
// find a Customer 
//

// select by id - it is supported multiple  keys
Customer bean = customerManager.findById(conn, 1); 

//
// update customer 
//

Customer bean = customerManager.findById(conn, 1); 
		
bean.setFirstName( "new name" );
		
// update all fields  
customerManager.store(conn, bean );
// update only field "firstName" 
customerManager.store(conn, bean, true, "firstName");
// update all fields except "firstName" and "lastName" 
customerManager.store(conn, bean, false, "firstName", "lastName");



//
// select customers
//

List<Customer> customers = new ArrayList<Customer>();
		
customerManager.find(conn, customers, "${firstName} LIKE ? OR ${lastName}=? ORDER BY #{lastName}", "b%", "sorrentino");

//
// remove customer
//

customerManager.removeById(conn, 1);

```
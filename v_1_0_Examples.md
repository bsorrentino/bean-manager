## BASIC EXAMPLES ##

The easier way to learn the features of BeanManager framework is **By Example** therefore, below, we try to build an real-use-case example.

### Customer & Bank Account management ###
At start we have identified two beans:
  1. Customer
  1. BankAccount

The Relation is **one** Customer **has one or more** BankAccounts. Outlinde below we show the typical steps that are prerequisites to build an application

  1. **Create the Beans and related BeanInfo (i.e. ORM metadata)**
  1. **Create Database programmatically (i.e. using [ddltils](http://db.apache.org/ddlutils/) and reusing the metadata already coded)**
  1. **Create,Update,find and remove beans**
  1. **Create a bean that impersonate a JOIN relation between Customer & Account**

### Create the Beans and related BeanInfo ###

#### Customer ####
```
public class Customer {

	private String firstName;
	private String lastName;
	private int id;
	private int accountId;
        private boolean vip = false;
        
	public final String getFirstName() { return firstName; 	}

	public final void setFirstName(String firstName) { this.firstName = firstName; }

	public final String getLastName() { return lastName; }

	public final void setLastName(String lastName) { this.lastName = lastName; }

	public final int getCustomerId() { return id; }

	public final void setCustomerId(int id) { this.id = id;	}

        public final int getAccountId() { return accountId; }

	public final void setAccountId(int value) { this.accountId = value; }

        public boolean isVip() { return vip; }

        public void setVip(boolean vip) { this.vip = vip; }	
}
```
#### CustomerBeanInfo ####
```

public class CustomerBeanInfo extends AbstractManagedBeanInfo<Customer> {

 public CustomerBeanInfo() { super(Customer.class); }

 public BeanDescriptor getBeanDescriptor() { return new BeanDescriptorEntity(getBeanClass(), "CUSTOMER"); }

 public PropertyDescriptor[] getPropertyDescriptors() {
  try {
   return new PropertyDescriptor[] {					
	new PropertyDescriptorPK("id", getBeanClass(), "getCustomerId", "setCustomerId" ),
	new PropertyDescriptorField("firstName", getBeanClass(), "getFirstName", "setFirstName" ).setFieldName("FIRST_NAME"),
	new PropertyDescriptorField("lastName", getBeanClass(), "getLastName", "setLastName" ).setFieldName("LAST_NAME"),
	new PropertyDescriptorField("account_id", getBeanClass(), "getAccountId", "setAccountId" ).setSQLType(Types.INTEGER),
	new PropertyDescriptorField("vip", getBeanClass(), "isVip", "setVip" ).setSQLType(Types.CHAR).setAdapter( new CharBooleanAdapter())
     };
   } catch (IntrospectionException e) {
	logger.warning( "getPropertyDescriptors " + e.getMessage() );		
   }
		
   return EMPTY_PROPERTYDESCRIPTOR;
 }
	
}
```

#### BankAccount ####
```
public class BankAccount {
    private int id;
    private int balance;
    private String number;
            
    public final int getAccountId() { return id; }        

    public final void setAccountId(int value ) { id = value; }        

    public final int getBalance() { return balance; }        

    public final void setBalance(int value ) { balance = value; }        

    public final String getNumber() { return number; }        

    public final void setNumber(String value ) { number = value; }                    
}
```

#### BankAccountBeanInfo ####
```
public class BankAccountBeanInfo extends AbstractManagedBeanInfo<BankAccount> {
    
    public BankAccountBeanInfo() { super( BankAccount.class ); } 
    
    public BeanDescriptor getBeanDescriptor() { return new BeanDescriptorEntity(getBeanClass(), "ACCOUNT"); }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            
            return new PropertyDescriptor[] {
                new PropertyDescriptorPK( "id", getBeanClass(), "getAccountId", "setAccountId"),
                new PropertyDescriptorField( "balance", getBeanClass(), "getBalance", "setBalance"),
                new PropertyDescriptorField( "number", getBeanClass(), "getNumber", "setNumber")                
            };
            
        }
        catch( Exception ex ) {
	   logger.warning( "getPropertyDescriptors " + e.getMessage() );		
        }
		
        return EMPTY_PROPERTYDESCRIPTOR;
    }
}
```

### Create Database programmatically ###

_Coming Soon_

### Create,Update,find and remove beans ###

_Coming Soon_

### Create a bean that impersonate a JOIN relation between Customer & Account ###

#### CustomerAccount ####
This bean impersonate the relation betwwen Customer & Account, it inherits Customer and add Bank Account information

```
public class CustomerAccount extends Customer {
    private int account;
    private String number;

    public int getBalance() {
        return account;
    }

    public void setBalance(int account) {
        this.account = account;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
```

The Related beaninfo inherits from CustomerBeanInfo add both the Join information (see in getBeanDescriptor method) and the new attributes

```
public class CustomerAccountBeanInfo extends CustomerBeanInfo {
    static final String JOIN_TABLE = "ACCOUNT";

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptorEntity descriptor =  (BeanDescriptorEntity) super.getBeanDescriptor();
        
        setBeanClass( CustomerAccount.class );
        
        descriptor.createJoinRelation(JOIN_TABLE, 
                    new JoinCondition("id","id") );
        return descriptor;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            
            return BeanManagerUtils.joinProperties( super.getPropertyDescriptors(), new PropertyDescriptor[] {
                new PropertyDescriptorJoin( "balance", getBeanClass(), "getBalance", "setBalance").setJoinTable(JOIN_TABLE),
                new PropertyDescriptorJoin( "number", getBeanClass(), "getNumber", "setNumber").setJoinTable(JOIN_TABLE)                
            });
            
        }
        catch( Exception ex ) {
            throw new IllegalStateException(ex);
        } 
        
    }


}
```
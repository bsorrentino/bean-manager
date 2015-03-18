# Introduction #

### MyEntityBean2 declaration ###

```

@Entity
@Table( name="JOINED_ENTITY")
@Inheritance(strategy=InheritanceType.JOINED)
public class MyEntityBean2 {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private String id;

    private String property2_1;

    private String property2_2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty2_1() {
        return property2_1;
    }

    public void setProperty2_1(String property2_1) {
        this.property2_1 = property2_1;
    }

    public String getProperty2_2() {
        return property2_2;
    }

    public void setProperty2_2(String property2_2) {
        this.property2_2 = property2_2;
    }

}

```

### MyEntityBean1 declaration ###

```

@Entity
@Table( name="MASTER_ENTITY")
public class MyEntityBean1 extends MyEntityBean2 {

    @Id
    private String id;

    private String property1_1;
    
    private String property1_2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty1_1() {
        return property1_1;
    }

    public void setProperty1_1(String property1_1) {
        this.property1_1 = property1_1;
    }

    public String getProperty1_2() {
        return property1_2;
    }

    public void setProperty1_2(String property1_2) {
        this.property1_2 = property1_2;
    }


}

```

### MyEntityBean1 usage ###

```

        BeanManager<MyEntityBean1> myEntityBean1Manager = JPABeanManagerFactory.createBeanManager(MyEntityBean1.class);
 
        String id = null;

    	{
    		MyEntityBean1 bean1 = new MyEntityBean1();
    		bean1.setProperty1_1("@1.1");
    		bean1.setProperty1_2("@1.2");
    		bean1.setProperty2_1("@2.1");
    		bean1.setProperty2_2("@2.2");
    		
    		myEntityBean1Manager.create(conn, bean1);
    		
    		id = bean1.getId();
    		
    	}
   	
    	{
    		MyEntityBean1 bean = myEntityBean1Manager.findById(conn, id);
    		
    		assertNotNull( bean );
    		
    	}
    	
    	{
    		
    		myEntityBean1Manager.removeById(conn, id);
    	}
        
  

```
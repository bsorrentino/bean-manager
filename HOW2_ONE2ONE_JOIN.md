#How to define a One to One Join

# Introduction #


### MyData Bean Declaration ###

```

@Entity
@Table(name="DATA")
public class MyData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private long balance;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MyData)) { return false; }
        MyData other = (MyData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bsc.bean.jpa.MyData[id=" + id + "]";
    }

}

```

### MyUser Bean Declaration ###

```

@Entity
@Table(name="USER")
public class MyUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    @OneToOne
    @JoinColumn( referencedColumnName="ID",name="data")
    private MyData data ;

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }

 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("org.bsc.bean.jpa.User[id=%s][name=%s]", id, name);
    }

}

```

### MyUser Usage ###

```

        BeanManager<MyUser> myUserManager = JPABeanManagerFactory.createBeanManager(MyUser.class);
        BeanManager<MyData> myDataManager = JPABeanManagerFactory.createBeanManager(MyData.class);
        
        assertNotNull( myUserManager );
        assertNotNull( myDataManager );

        MyData data = new MyData();
        data.setBalance(1000L);

        myDataManager.create(conn, data);

        MyUser us = new MyUser();
        us.setName( "BSC");
        us.setData( data );
        
        myUserManager.create(conn, us);

        MyUser us1 = myUserManager.findById(conn, us.getId());

        assertEquals( us.getName(), us1.getName());
        assertEquals( "BSC", us1.getName());
        assertEquals( data.getId(), us.getData().getId());

        data.setBalance( 1200 );
        myDataManager.store(conn, data);
        
        us1 = myUserManager.findById(conn, us.getId());

        assertEquals( us.getName(), us1.getName());
        assertEquals( "BSC", us1.getName());
        assertEquals( data.getId(), us.getData().getId());
        assertEquals( 1200L, us.getData().getBalance());
        

        Collection<MyUser> users = myUserManager.findAll(conn, new ArrayList<MyUser>(), null);

```
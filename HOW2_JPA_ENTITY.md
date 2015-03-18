#How to create entity bean using JPA

# Introduction #


### MyUser Bean Declaration ###

```

@Entity
@Table(name="USER");
public class MyUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

 
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

### MyUser Bean usage ###

```

        BeanManager<MyUser> myUserManager = JPABeanManagerFactory.createBeanManager(MyUser.class);


```
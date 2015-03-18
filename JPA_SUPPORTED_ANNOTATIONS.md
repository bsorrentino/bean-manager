#Supported JPA Annotations

## ANNOTATIONS TABLE ##

### Release 1.4-beta1 ###

| **Annotation** | **constraints** | **release** |
|:---------------|:----------------|:------------|
| @Entity | - | 1.4-beta1 |
| @Table | only **name** attribute supported   | 1.4-beta1 |
| @Transient | -   | 1.4-beta1 |
| @Id | -   | 1.4-beta1 |
| @GeneratedValue | only **strategy** = **GenerationType.AUTO** supported. In that case you must define field as String | 1.4-beta1 |
| @Column | only **insertable**, **updatable**, **name** and **length** are supported | 1.4-beta1 |
|  @OneToOne | - | 1.4-beta1 |
|  @JoinColumn | **referencedColumnName** and **name** are mandatory | 1.4-beta1 |

### Release 1.4-beta2 ###

| **Annotation** | **constraints** | **release** |
|:---------------|:----------------|:------------|
| @Table | only **name** and **schema** attribute supported   | 1.4-beta2 |
| @Inheritance | only **strategy**=**InheritanceType.JOINED** and  **strategy**=**InheritanceType.SINGLE\_TABLE** are supported | 1.4-beta2 |
| @MappedSuperclass | - | 1.4-beta2 |
# Introduction #

Below is shown an utility class that acts like bridge between BeanManager and [DdlUtils](http://db.apache.org/ddlutils/) package. This will be very useful to **CREATE** a Database from **Bean Info** but , with minimal effort, will be also possible **ALTER** Database according con **Bean Info** meta-data.

## DDLUtils utility class ##
```
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.bsc.bean.BeanDescriptorUtils;
import org.bsc.bean.PropertyDescriptorField;
import org.bsc.bean.PropertyDescriptorJoin;
import org.bsc.bean.PropertyDescriptorPK;

/**
 *
 * @author sorrentino
 */
public class DDLUtils {
    
 /**
  * 
  * @param pd
  * @return
  */
 public static Column fromPropertyDescriptorToColumn( PropertyDescriptor pd ) {
	
	Column result = null;
	
	if( pd instanceof PropertyDescriptorField ) {
	
	        if( pd instanceof PropertyDescriptorJoin ) return result;
	
	        PropertyDescriptorField f = (PropertyDescriptorField)pd;
	
	        result  = new Column();
	
	        result.setName( f.getFieldName() );
	        if( f.getSize()>0 ) result.setSizeAndScale( f.getSize(), 0 );
	        result.setTypeCode( f.getSQLType() );
	        result.setRequired(true);
	        result.setPrimaryKey( f instanceof PropertyDescriptorPK );
	
	}	
	
	return result;
 }
	
 /**
 * Create a Table definition from bean info
 * @param info
 * @return Table definition
 */
 public static Table fromBeanInfoToTable( BeanInfo info ) {
		
	Table t = new Table();
		
	BeanDescriptor bd = info.getBeanDescriptor();
		
	String name = BeanDescriptorUtils.getEntityName(bd);
		
	if( null==name ) throw new IllegalArgumentException( "info is not a vaid beanManager beanInfo!");
		
	t.setName(name);
		
		
	for( PropertyDescriptor pd : info.getPropertyDescriptors() ) {

		Column c = fromPropertyDescriptorToColumn( pd );
			
		if( null!=c ) {
			t.addColumn(c);
		}
	}
		
	return t;
 }
	
 /**
 *
 *
 */	
 public static void createTablesModel( Database db, BeanInfo ...infos ) {
	
 	for( BeanInfo info : infos ) {
			
		db.addTable( fromBeanInfoToTable(info) );
			
	}
 }

}

```

## How To Create a DB ##

The following example shown how to create a Database using the Bridge between DdlUtils and BeanManager. **This example uses the DerbyDb embedded**

```

static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
static final String connectionUrl = "jdbc:derby:/testDB";

/**
* @param args
*/
public static void main(String[] args) {

Connection conn = null;
        
try {
			
 Class.forName(DRIVER);
            
 Properties p = new Properties();
 p.setProperty("create", "true");
    	
 conn = DriverManager.getConnection(connectionUrl, p);
            
 conn.setAutoCommit(true);
            
 Database db = new Database(); 
        	
 DDLUtils .createTablesModel( db, new AuditedEventBeanInfo() );
            
 Platform platform = PlatformFactory.createNewPlatformInstance(DRIVER, connectionUrl);
    		
 platform.createTables( conn, db,  true /*drop tables */, true /* continue on error */ );
            
} catch (Exception e) {
   e.printStackTrace();
}
finally {
 if( null!=conn )
   try { conn.close(); } catch (SQLException e) { // TODO warning }
}
		
	
}


```
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bsc.bean.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;
import org.bsc.util.Log;


/**
 *
 * @author Sorrentino
 */
public class BaseTestUtils extends DDLUtils {

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver"; 
    public static final String CONNECTION_URL = "jdbc:derby:./target/DerbyDB/testDB"; ;
    
    public static void loadDriver() throws Exception {    
            Class.forName(DRIVER);         
    }
    
    public static void initLogger() throws Exception {
            LogManager.getLogManager().readConfiguration( TestBeanManager.class.getResourceAsStream("/logging.properties"));

            {
            final Handler h = new FileHandler("target/bsc-command.%u.%g.log", 1024*1024, 10, true) {{ 
                this.setFormatter(new SimpleFormatter());
            }};
            Log.cmd.addHandler( h );
            Log.cmd.setLevel(Level.FINEST);
            }
            
            {
            final Handler h = new FileHandler("target/bsc-out.%u.%g.log", 1024*1024, 10, true) {{ 
                this.setFormatter(new SimpleFormatter());
            }};
            Log.out.addHandler( h );
            Log.out.setLevel(Level.FINEST);
            }
            //Log.out.addHandler(h);
            
    }
    
    public static Connection connect() throws Exception {
            
            Properties p = new Properties();
            p.setProperty("create", "true");
		
	    return DriverManager.getConnection(CONNECTION_URL, p); 
    }
    
    /**
     * 
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void disconnect( Connection conn ) throws Exception {

            if( null!=conn ) {
                    conn.close();
            }


    }

}

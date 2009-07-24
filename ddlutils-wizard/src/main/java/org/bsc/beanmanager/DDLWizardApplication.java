package org.bsc.beanmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jdesktop.application.Application;
import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Summary;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardBranchController;
import org.netbeans.spi.wizard.WizardException;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelProvider;
import org.netbeans.spi.wizard.WizardPage.WizardResultProducer;
import org.swixml.jsr296.SwingApplication;
import static org.bsc.beanmanager.DDLWizardConstants.*;

public class DDLWizardApplication extends SwingApplication {

	private static final boolean USE_BRANCH = false;
	
	SetJDBCInfoPage page1 = new SetJDBCInfoPage();
	SetDBSchema page2 = new SetDBSchema();
	GenerateBean page3 = new GenerateBean();
	
	WizardResultProducer producer = new WizardResultProducer(){

        public Object finish(Map settings) throws WizardException {


            final Map<String,Object> map = settings;
            String [] summary = new String[ map.size() ];
                    int i=0;
            for( Map.Entry<String,Object> e : map.entrySet() ) {
                summary[i++] = String.format("%s=%s", e.getKey(), e.getValue());
            }

            return Summary.create( summary, settings );

        }

        public boolean cancel(Map wizardData) {
            exit();
            return true;
        }
     
    };
	
	
	
        public static boolean isEmpty( String value ) {

            if( value==null ) return true;
            if( value.length()==0) return true;
            if( value.trim().length()==0 ) return true;

            return false;
        }

	public static Connection getConnection( String driverClass, String connectionUrl, String user, String password ) throws Exception {
		try {
			Class.forName( driverClass );
		} catch (ClassNotFoundException e) {
			throw new Exception( "Error Loading Driver!");
		}

		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(connectionUrl, user, password );
			
		} catch (SQLException e) {
			throw new Exception( "Error opening connection !" );
		}
		
		return conn;
	}
	
	public static void closeConnection( Connection conn ) {
		if( null==conn ) return;
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Log
		}
	}
	
	
    @Override
    protected void startup() {

        try {
            render(page1, "SetJDBCInfoPage.xml");
            render( page2, "SetDBSchema.xml");
            render( page3, "GenerateBean.xml");
            
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Fatal Error on render component!", "Error", JOptionPane.ERROR_MESSAGE);
            exit();

        }
        
        Wizard wizard = null;   
        

        wizard = WizardPage.createWizard( new WizardPage[] { page1, page2, page3 }, producer );
 
      
      /* Object result = */WizardDisplayer.showWizard(wizard);

   }

    @Override
    protected void initialize(String[] args) {
        boolean nimbusLF = false;

        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            System.out.println(laf.getName());

            if ("Nimbus".equals(laf.getName())) {
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                    nimbusLF = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if( !nimbusLF ) {
            String lf = UIManager.getCrossPlatformLookAndFeelClassName();

            System.out.println(lf);
            try {
                UIManager.setLookAndFeel(lf);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         }
    }

	
	public static void main( String args[] ) {

		Application.launch(DDLWizardApplication.class, args);
	}
}

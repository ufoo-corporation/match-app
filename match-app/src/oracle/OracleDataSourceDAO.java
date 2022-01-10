package oracle; 

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.SQLException;

public class OracleDataSourceDAO extends OracleDataSource  { 
    private static OracleDataSourceDAO ods; 
     

   private OracleDataSourceDAO() throws SQLException {
       
   }
    
   public static OracleDataSourceDAO getOracleDataSourceDAO() throws FileNotFoundException { 
        FileInputStream fichier = null;
        
        try {
            Properties props = new Properties();
            fichier = new FileInputStream(
                ".\\src\\oracle\\connexion.properties"
            );
            props.load(fichier);
            ods = new OracleDataSourceDAO();
            ods.setDriverType(props.getProperty("pilote"));
            ods.setPortNumber(new Integer(props.getProperty("port")));
            ods.setServiceName(props.getProperty("service"));
            ods.setUser(props.getProperty("user"));
            ods.setPassword(props.getProperty("pwd"));
            ods.setServerName(props.getProperty("serveur"));
        } catch (SQLException | IOException ex) { 
            System.out.println(ex.getMessage());
        } finally { 
            try { 
                if (fichier != null){
                    fichier.close();
                }
            } catch (IOException ex) { 
                
            }
        } 
        return ods; 
    } 
}
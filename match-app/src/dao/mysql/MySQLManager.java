package dao.mysql;

import java.io.FileInputStream; 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;

public class MySQLManager {
    private static Connection connection;
    
    public static void connect(){
        Connection result = null;
        
        FileInputStream file = null;
         
        try{
            Properties props = new Properties();
            file = new FileInputStream(
                ".\\src\\dao\\mysql\\connexion.properties"
            );
            props.load(file);
            
            String server = props.getProperty("server");
            String port = props.getProperty("port");
            String db = props.getProperty("db");
            String url = String.format("jdbc:mysql://%s:%s/%s", server, port, db);
            
            String usr = props.getProperty("usr");
            String pwd = props.getProperty("pwd");
            
            result = DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException | IOException ex){
            System.out.println(ex);
        } finally{
            try {
                if (file != null){
                    file.close();
                }
            } catch (IOException ex) { 
                System.out.println(ex);
            }
        }
        
        if(result != null){
            System.out.println("Connection successful");
        }else{
            System.out.println("Connection failled");
        }
        
        connection = result;
    }
    
    public static Connection getConnection(){
        return connection;
    }
}

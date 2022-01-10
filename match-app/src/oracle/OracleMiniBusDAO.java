package oracle;

import java.util.List;
import dao.IMiniBusDAO;
import metier.MiniBus;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OracleMiniBusDAO implements IMiniBusDAO {
    private static DataSource ds;

    private static Connection connexionBD;
    
    @Override 
    public List<MiniBus > getLesMinibus() { 
        ResultSet resultSet = null; 
        Statement stmt = null; 
        List<MiniBus> listeMinibus = null; 

        try { 
            stmt = connexionBD.createStatement(); 
            listeMinibus = new ArrayList<>(); 

            resultSet = stmt.executeQuery("SELECT * FROM minibus");
            
            while (resultSet.next()) {
                int noMinibus = resultSet.getInt("nominibus");
                int capacite = resultSet.getInt("capacite");
                listeMinibus.add(new MiniBus(noMinibus, capacite));
            }
        } catch (SQLException exc) { 
            
        } finally { 
            try {
                if (stmt != null){
                    stmt.close();
                }

                if (resultSet != null){
                    resultSet.close(); 
                }
            } catch (SQLException ex) { 
                
            } 
        } 

        return listeMinibus; 
    }
    
    @Override
    public void creerMinibus() {
        
    }
    
    @Override
    public void supprimerMiniBus(int numMiniBus) {
        
    }
    
    @Override 
    public void setDataSource(DataSource ds) { 
        OracleMiniBusDAO.ds = ds; 
    } 

    @Override 
    public void setConnection(Connection c){ 
        OracleMiniBusDAO.connexionBD = c; 
    }
}
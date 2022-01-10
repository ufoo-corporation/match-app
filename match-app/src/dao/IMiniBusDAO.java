package dao;

import java.util.List;
import metier.MiniBus;
import javax.sql.DataSource;
import java.sql.Connection; 

public interface IMiniBusDAO {
    public List<MiniBus> getLesMinibus();
    
    public void creerMinibus();
    
    public void supprimerMiniBus(int numMiniBus);
    
    public void setDataSource(DataSource ds);
    
    public void setConnection(Connection c);
}
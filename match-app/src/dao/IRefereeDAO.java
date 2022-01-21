package dao;

import java.util.List;
import metier.Referee;
import java.sql.Connection;

public interface IRefereeDAO {
    public List<Referee> getReferees();
    
    public Referee getReferee(int id);
    
    public void createReferee(Referee referee);
    
    public void deleteReferee(int id);
    
    public void setConnection(Connection connexion);
}

package dao;

import java.util.List;
import metier.Referee;
import java.sql.Connection;

public interface IRefereeOfGameDAO {
    public List<Referee> getRefereesOfGame(int gameId);
    
    public void createRefereesOfGame(int gameId, List<Referee> referees);
    
    public void deleteRefereesOfGame(int gameId);
    
    public void setConnection(Connection connexion);
    
    public void setRefereeDAO(IRefereeDAO refereeDAO);
}

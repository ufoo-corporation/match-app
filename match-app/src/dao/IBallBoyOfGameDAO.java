package dao;

import java.util.List;
import metier.BallBoy;
import java.sql.Connection;

public interface IBallBoyOfGameDAO {
    public List<BallBoy> getBallBoysOfGame(int gameId);
    
    public void createBallBoysOfGame(int gameId, List<BallBoy> ballBoys);
    
    public void deleteBallBoysOfGame(int gameId);
    
    public void setConnection(Connection connexion);
    
    public void setBallBoyDAO(IBallBoyDAO ballBoyDAO);
}

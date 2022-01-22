package dao;

import java.util.List;
import metier.BallBoy;
import java.sql.Connection;

public interface IBallBoyDAO {
    public List<BallBoy> getBallBoys();
    
    public BallBoy getBallBoy(int id);
    
    public void createBallBoy(BallBoy ballBoy);
    
    public void setConnection(Connection connexion);
}

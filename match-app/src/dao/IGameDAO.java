package dao;

import java.util.List;
import metier.Game;
import java.sql.Connection;

public interface IGameDAO {
    public List<Game> getGames(int courtIndex);
    
    public void createGame(Game game);
    
    public void deleteGame(int id);
    
    public void setConnection(Connection connexion);
    
    public void setPlayerDAO(IPlayerDAO playerDAO);
    
    public void setRefereeDAO(IRefereeDAO refereeDAO);
    
    public void setRefereeOfGame(IRefereeOfGameDAO refereeOfGameDAO);
    
    public void setBallBoyOfGame(IBallBoyOfGameDAO ballBoyOfGameDAO);
}

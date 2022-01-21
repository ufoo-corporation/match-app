package dao;

import java.util.List;
import metier.Game;
import java.sql.Connection;

public interface IGameDAO {
    public List<Game> getGames();
    
    public void createGame(Game game);
    
    public void deleteGame(int id);
    
    public void setConnection(Connection connexion);
    
    public void setPlayerDAO(IPlayerDAO playerDAO);
}

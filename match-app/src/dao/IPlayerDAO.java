package dao;

import java.util.List;
import metier.Player;
import java.sql.Connection;

public interface IPlayerDAO {
    public List<Player> getPlayers();
    
    public Player getPlayer(int id);
    
    public void createPlayer(Player player);
    
    public void deletePlayer(int id);
    
    public void setConnection(Connection connexion);
}

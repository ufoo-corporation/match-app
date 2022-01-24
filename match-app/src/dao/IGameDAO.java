package dao;

import java.util.List;
import metier.Game;
import java.sql.Connection;

public interface IGameDAO {
    public List<Game> getGames(int courtIndex);
    
    public List<Game> getGamesAtThatMoment(int date, int time, int courtIndexToNotCheck);
    
    public Game getGameAt(int date, int time, int courtIndex);
    
    public boolean gameExistAt(int date, int time, int courtIndex);
    
    public void createGame(Game game);
    
    public void updateGame(Game game);
    
    public void setConnection(Connection connexion);
    
    public void setPlayerDAO(IPlayerDAO playerDAO);
    
    public void setRefereeDAO(IRefereeDAO refereeDAO);
    
    public void setRefereeOfGame(IRefereeOfGameDAO refereeOfGameDAO);
    
    public void setBallBoyOfGame(IBallBoyOfGameDAO ballBoyOfGameDAO);
}

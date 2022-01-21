package dao.mysql;

import dao.IGameDAO;
import dao.IPlayerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Game;
import metier.Player;
import java.util.Date;
import java.time.LocalTime;

public class MySQLGameDAO implements IGameDAO {
    private Connection connection;
    private IPlayerDAO playerDAO;

    @Override
    public List<Game> getGames() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM game");
            
            ResultSet rs = statement.executeQuery();
            
            List<Game> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(gameFromDB(rs));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public void createGame(Game game) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO game (id, date, time, courtIndex, player1, player2, player3, player4) VALUES (null, ?, ?, ?, ?, ?, null, null");

            long dateInMs = game.getDate().getTime();
            LocalTime time = game.getTime();
            
            statement.setDate(1, new java.sql.Date(dateInMs));
            statement.setTime(2, java.sql.Time.valueOf(time));
            statement.setInt(3, game.getCourtIndex());
            statement.setInt(4, game.getPlayer1().getId());
            statement.setInt(5, game.getPlayer2().getId());
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteGame(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setPlayerDAO(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
    
    private Game gameFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            Date date = rs.getDate("date");
            LocalTime time = rs.getTime("time").toLocalTime();
            int courtIndex = rs.getInt("courtIndex");
            int player1Id = rs.getInt("player1");
            int player2Id = rs.getInt("player2");
            
            Player player1 = playerDAO.getPlayer(player1Id);
            Player player2 = playerDAO.getPlayer(player2Id);
            
            return new Game(id, date, time, courtIndex, player1, player2, null, null);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

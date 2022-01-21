package dao.mysql;

import dao.IBallBoyOfGameDAO;
import dao.IGameDAO;
import dao.IPlayerDAO;
import dao.IRefereeDAO;
import dao.IRefereeOfGameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.BallBoy;
import metier.Game;
import metier.Player;
import metier.Referee;

public class MySQLGameDAO implements IGameDAO {
    private Connection connection;
    private IPlayerDAO playerDAO;
    private IRefereeDAO refereeDAO;
    private IRefereeOfGameDAO refereeOfGameDAO;
    private IBallBoyOfGameDAO ballBoyOfGameDAO;

    @Override
    public List<Game> getGames(int courtIndex) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM game WHERE courtIndex = ? order by time, date");
            statement.setInt(1, courtIndex);
            
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO game (id, date, time, courtIndex, player1, player2, player3, player4, main_referee) VALUES (null, ?, ?, ?, ?, ?, null, null, ?)");
            
            statement.setInt(1, game.getDate());
            statement.setInt(2, game.getTime());
            statement.setInt(3, game.getCourtIndex());
            statement.setInt(4, game.getPlayer1().getId());
            statement.setInt(5, game.getPlayer2().getId());
            statement.setInt(6, game.getMainReferee().getId());
            
            statement.executeUpdate();
            
            statement = connection.prepareStatement("SELECT id FROM game WHERE date = ? and time = ? and courtIndex = ?");
            
            statement.setInt(1, game.getDate());
            statement.setInt(2, game.getTime());
            statement.setInt(3, game.getCourtIndex());
            
            ResultSet rs = statement.executeQuery();
            rs.next();
            int gameId = rs.getInt("id");
            
            refereeOfGameDAO.createRefereesOfGame(gameId, game.getReferees());
            ballBoyOfGameDAO.createBallBoysOfGame(gameId, game.getBallBoys());
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

    @Override
    public void setRefereeDAO(IRefereeDAO refereeDAO) {
        this.refereeDAO = refereeDAO;
    }

    @Override
    public void setRefereeOfGame(IRefereeOfGameDAO refereeOfGameDAO) {
        this.refereeOfGameDAO = refereeOfGameDAO;
    }

    @Override
    public void setBallBoyOfGame(IBallBoyOfGameDAO ballBoyOfGameDAO) {
        this.ballBoyOfGameDAO = ballBoyOfGameDAO;
    }
    
    private Game gameFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            int date = rs.getInt("date");
            int time = rs.getInt("time");
            int courtIndex = rs.getInt("courtIndex");
            int player1Id = rs.getInt("player1");
            int player2Id = rs.getInt("player2");
            int refereeId = rs.getInt("main_referee");
            
            Player player1 = playerDAO.getPlayer(player1Id);
            Player player2 = playerDAO.getPlayer(player2Id);
            
            Referee mainReferee = refereeDAO.getReferee(refereeId);
            List<Referee> referees = refereeOfGameDAO.getRefereesOfGame(id);
            List<BallBoy> ballBoys = ballBoyOfGameDAO.getBallBoysOfGame(id);
            
            return new Game(id, date, time, courtIndex, player1, player2, null, null, mainReferee, referees, ballBoys);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

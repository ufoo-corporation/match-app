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
import java.sql.Types;
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM game WHERE court_index = ? order by time, date");
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO game (id, date, time, court_index, round_index, player1, player2, player3, player4, score_team_1, score_team_2, main_referee) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setInt(1, game.getDate());
            statement.setInt(2, game.getTime());
            statement.setInt(3, game.getCourtIndex());
            statement.setInt(4, game.getRoundIndex());
            statement.setInt(5, game.getPlayer1().getId());
            statement.setInt(6, game.getPlayer2().getId());
            
            if(game.getPlayer3() == null){
                statement.setNull(7, Types.INTEGER);
            }else{
                statement.setInt(7, game.getPlayer3().getId());
            }
            
            if(game.getPlayer4() == null){
                statement.setNull(8, Types.INTEGER);
            }else{
                statement.setInt(8, game.getPlayer4().getId());
            }
            
            statement.setString(9, String.format("%d/%d/%d", game.getScore1Team1(), game.getScore2Team1(), game.getScore3Team1()));
            statement.setString(10, String.format("%d/%d/%d", game.getScore1Team2(), game.getScore2Team2(), game.getScore3Team2()));
            statement.setInt(11, game.getMainReferee().getId());
            
            statement.executeUpdate();
            
            statement = connection.prepareStatement("SELECT id FROM game WHERE date = ? and time = ? and court_index = ?");
            
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
            int courtIndex = rs.getInt("court_index");
            int roundIndex = rs.getInt("round_index");
            int player1Id = rs.getInt("player1");
            int player2Id = rs.getInt("player2");
            
            int player3Id = rs.getInt("player3");
            if(rs.wasNull()){
                player3Id = -1;
            }
            int player4Id = rs.getInt("player4");
            if(rs.wasNull()){
                player4Id = -1;
            }
            
            int refereeId = rs.getInt("main_referee");
            
            
            Player player1 = playerDAO.getPlayer(player1Id);
            Player player2 = playerDAO.getPlayer(player2Id);
            
            Player player3 = null;
            if(player3Id != -1){
                player3 = playerDAO.getPlayer(player3Id);
            }
            Player player4 = null;
            if(player4Id != -1){
                player4 = playerDAO.getPlayer(player4Id);
            }
            
            String scoreTeam1 = rs.getString("score_team_1");
            String scoreTeam2 = rs.getString("score_team_2");
            
            String[] scoresTeam1 = scoreTeam1.split("/");
            String[] scoresTeam2 = scoreTeam2.split("/");
            
            int score1Team1 = Integer.parseInt(scoresTeam1[0]);
            int score2Team1 = Integer.parseInt(scoresTeam1[1]);
            int score3Team1 = Integer.parseInt(scoresTeam1[2]);
            int score1Team2 = Integer.parseInt(scoresTeam2[0]);
            int score2Team2 = Integer.parseInt(scoresTeam2[1]);
            int score3Team2 = Integer.parseInt(scoresTeam2[2]);
            
            Referee mainReferee = refereeDAO.getReferee(refereeId);
            List<Referee> referees = refereeOfGameDAO.getRefereesOfGame(id);
            List<BallBoy> ballBoys = ballBoyOfGameDAO.getBallBoysOfGame(id);
            
            return new Game(id, date, time, courtIndex, roundIndex, player1, player2, player3, player4, score1Team1, score2Team1, score3Team1, score1Team2, score2Team2, score3Team2, mainReferee, referees, ballBoys);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

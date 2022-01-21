package dao.mysql;

import dao.IBallBoyDAO;
import dao.IBallBoyOfGameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.BallBoy;
import metier.Referee;

public class MySQLBallBoyOfGameDAO implements IBallBoyOfGameDAO{
    private Connection connection;
    private IBallBoyDAO ballBoyDAO;
    
    @Override
    public List<BallBoy> getBallBoysOfGame(int gameId) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ball_boy_of_game WHERE game_id = ?");
            statement.setInt(1, gameId);
            
            ResultSet rs = statement.executeQuery();
            
            List<BallBoy> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(ballBoyDAO.getBallBoy(rs.getInt("ball_boy_id")));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public void createBallBoysOfGame(int gameId, List<BallBoy> ballBoys) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ball_boy_of_game (game_id, ball_boy_id) VALUES (?, ?)");

            for(BallBoy ballBoy : ballBoys){
                statement.setInt(1, gameId);
                statement.setInt(2, ballBoy.getId());

                statement.executeUpdate();
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteBallBoysOfGame(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setConnection(Connection connexion) {
        this.connection = connexion;
    }

    @Override
    public void setBallBoyDAO(IBallBoyDAO ballBoyDAO) {
        this.ballBoyDAO = ballBoyDAO;
    }
}

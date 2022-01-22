package dao.mysql;

import dao.IRefereeDAO;
import dao.IRefereeOfGameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Game;
import metier.Referee;

public class MySQLRefereeOfGameDAO implements IRefereeOfGameDAO {
    Connection connection;
    IRefereeDAO refereeDAO;
    
    @Override
    public List<Referee> getRefereesOfGame(int gameId) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM referee_of_game WHERE game_id = ?");
            statement.setInt(1, gameId);
            
            ResultSet rs = statement.executeQuery();
            
            List<Referee> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(refereeDAO.getReferee(rs.getInt("referee_id")));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public void createRefereesOfGame(int gameId, List<Referee> referees) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO referee_of_game (game_id, referee_id) VALUES (?, ?)");

            for(Referee referee : referees){
                statement.setInt(1, gameId);
                statement.setInt(2, referee.getId());

                statement.executeUpdate();
            }
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteRefereesOfGame(int gameId) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM referee_of_game where game_id = ?");

            statement.setInt(1, gameId);
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void setConnection(Connection connexion) {
        this.connection = connexion;
    }

    @Override
    public void setRefereeDAO(IRefereeDAO refereeDAO) {
        this.refereeDAO = refereeDAO;
    }
}

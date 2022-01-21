package dao.mysql;

import dao.IPlayerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Player;

public class MySQLPlayerDAO implements IPlayerDAO {
    private Connection connection;

    @Override
    public List<Player> getPlayers() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM player");
            
            ResultSet rs = statement.executeQuery();
            
            List<Player> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(playerFromDB(rs));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public Player getPlayer(int id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM player WHERE id = ?");
            
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            
            return playerFromDB(rs);
        } catch (SQLException ex){
            System.out.println(ex);
        }   
        
        return null;
    }

    @Override
    public void createPlayer(Player player) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO player (id, first_name, name, nationality) VALUES (null, ?, ?, ?");

            statement.setString(1, player.getFirstName());
            statement.setString(2, player.getName());
            statement.setString(3, player.getNationality());
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deletePlayer(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private Player playerFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String name = rs.getString("name");
            String nationality = rs.getString("nationality");
            
            return new Player(id, firstName, name, nationality);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

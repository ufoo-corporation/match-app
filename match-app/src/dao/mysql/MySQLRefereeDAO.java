package dao.mysql;

import dao.IRefereeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Referee;

public class MySQLRefereeDAO implements IRefereeDAO {
    private Connection connection;

    @Override
    public List<Referee> getReferees() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM referee");
            
            ResultSet rs = statement.executeQuery();
            
            List<Referee> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(refereeFromDB(rs));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public Referee getReferee(int id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM referee WHERE id = ?");
            
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            
            return refereeFromDB(rs);
        } catch (SQLException ex){
            System.out.println(ex);
        }   
        
        return null;
    }

    @Override
    public void createReferee(Referee referee) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO referee (id, first_name, namen nationality, level) VALUES (null, ?, ?, ?, ?");

            statement.setString(1, referee.getFirstName());
            statement.setString(2, referee.getName());
            statement.setString(3, referee.getNationality());
            statement.setString(4, referee.getLevel());
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteReferee(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private Referee refereeFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String name = rs.getString("name");
            String nationality = rs.getString("nationality");
            String level = rs.getString("level");
            
            return new Referee(id, firstName, name, nationality, level);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

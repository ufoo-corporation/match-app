package dao.mysql;

import dao.IBallBoyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.BallBoy;

public class MySQLBallBoyDAO implements IBallBoyDAO {
    private Connection connection;

    @Override
    public List<BallBoy> getBallBoys() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ball_boy");
            
            ResultSet rs = statement.executeQuery();
            
            List<BallBoy> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(ballBoyFromDB(rs));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public BallBoy getBallBoy(int id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ball_boy WHERE id = ?");
            
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            
            return ballBoyFromDB(rs);
        } catch (SQLException ex){
            System.out.println(ex);
        }   
        
        return null;
    }

    @Override
    public void createBallBoy(BallBoy ballBoy) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ball_boy (id, first_name, name) VALUES (null, ?, ?)");

            statement.setString(1, ballBoy.getFirstName());
            statement.setString(2, ballBoy.getName());
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteBallBoy(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private BallBoy ballBoyFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String name = rs.getString("name");
            
            return new BallBoy(id, firstName, name);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

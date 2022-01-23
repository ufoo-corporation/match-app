package dao.mysql;

import dao.IPlayerDAO;
import dao.IReservationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Player;
import metier.Reservation;

public class MySQLReservationDAO implements IReservationDAO {
    private Connection connection;
    private IPlayerDAO playerDAO;
    
    @Override
    public List<Reservation> getReservations(int courtIndex) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservation WHERE court_index = ?");
            
            statement.setInt(1, courtIndex);
            
            ResultSet rs = statement.executeQuery();
            
            List<Reservation> result = new ArrayList<>();
            
            while(rs.next()){
                result.add(reservationFromDB(rs));
            }
            
            return result;
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public boolean reservationExistAt(int date, int time, int courtIndex) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM reservation WHERE date = ? and time = ? and court_index = ?");
            statement.setInt(1, date);
            statement.setInt(2, time);
            statement.setInt(3, courtIndex);
            
            ResultSet rs = statement.executeQuery();
            
            return rs.next();
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return true;
    }

    @Override
    public boolean playerHasReservation(int playerId) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM reservation WHERE player = ?");
            statement.setInt(1, playerId);
            
            ResultSet rs = statement.executeQuery();
            
            return rs.next();
        }catch (SQLException ex){
            System.out.println(ex);
        }
        
        return true;
    }

    @Override
    public void createReservation(Reservation reservation) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservation (id, date, time, court_index, player) VALUES (null, ?, ?, ?, ?)");

            statement.setInt(1, reservation.getDate());
            statement.setInt(2, reservation.getTime());
            statement.setInt(3, reservation.getCourtIndex());
            statement.setInt(4, reservation.getPlayer().getId());
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteReservation(int id) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservation WHERE id = ?");

            statement.setInt(1, id);
            
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
    public void setPlayerDAO(IPlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
    
    private Reservation reservationFromDB(ResultSet rs){
        try{
            int id = rs.getInt("id");
            int date = rs.getInt("date");
            int time = rs.getInt("time");
            int courtIndex = rs.getInt("court_index");
            int playerId = rs.getInt("player");
            
            Player player = playerDAO.getPlayer(playerId);
            
            return new Reservation(id, date, time, courtIndex, player);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

package dao;

import java.util.List;
import java.sql.Connection;
import metier.Reservation;

public interface IReservationDAO {
    public List<Reservation> getReservations(int courtIndex);
    
    public void createReservation(Reservation reservation);
    
    public void deleteReservation(int id);
    
    public void setConnection(Connection connexion);
    
    public void setPlayerDAO(IPlayerDAO playerDAO);
}

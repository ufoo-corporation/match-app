package dao;

import java.util.List;
import java.sql.Connection;
import metier.Reservation;

public interface IReservationDAO {
    public List<Reservation> getReservations(int courtIndex);
    
    public boolean reservationExistAt(int date, int time, int courtIndex);
    
    public boolean playerHasReservation(int playerId);
    
    public void createReservation(Reservation reservation);
    
    public void deleteReservation(int id);
    
    public void setConnection(Connection connexion);
    
    public void setPlayerDAO(IPlayerDAO playerDAO);
}

package metier;

import java.time.LocalTime;
import java.util.Date;

public class Game {
    private int id;
    private Date date;
    private LocalTime time;
    private int courtIndex;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    
    public Game(int id, Date date, LocalTime time, int courtIndex, Player player1, Player player2, Player player3, Player player4){
        this.id = id;
        this.time = time;
        this.date = date;
        this.courtIndex = courtIndex;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }
    
    public int getId(){
        return id;
    }
    
    public Date getDate(){
        return date;
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    public int getCourtIndex(){
        return courtIndex;
    }
    
    public Player getPlayer1(){
        return player1;
    }
    
    public Player getPlayer2(){
        return player2;
    }
    
    public Player getPlayer3(){
        return player3;
    }
    
    public Player getPlayer4(){
        return player4;
    }
}

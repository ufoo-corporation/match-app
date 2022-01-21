package metier;

import java.util.List;

public class Game {
    private int id;
    private int date;
    private int time;
    private int courtIndex;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Referee mainReferee;
    private List<Referee> referees;
    private List<BallBoy> ballBoys;
    
    public Game(int id, int date, int time, int courtIndex, Player player1, Player player2, Player player3, Player player4, Referee mainReferee, List<Referee> referees, List<BallBoy> ballBoys){
        this.id = id;
        this.time = time;
        this.date = date;
        this.courtIndex = courtIndex;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.mainReferee = mainReferee;
        this.referees = referees;
        this.ballBoys = ballBoys;
    }
    
    public int getId(){
        return id;
    }
    
    public int getDate(){
        return date;
    }
    
    public int getTime(){
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
    
    public Referee getMainReferee(){
        return mainReferee;
    }
    
    public List<Referee> getReferees(){
        return referees;
    }
    
    public List<BallBoy> getBallBoys(){
        return ballBoys;
    }
    
    public String toDisplayString(){
        return String.format("%s vs %s", player1.getName().toUpperCase(), player2.getName().toUpperCase());
    }
}

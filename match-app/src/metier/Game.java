package metier;

import java.util.List;

public class Game {
    private final int id;
    private final int date;
    private final int time;
    private final int courtIndex;
    private final int roundIndex;
    private final Player player1;
    private final Player player2;
    private final Player player3;
    private final Player player4;
    private final int score1Team1;
    private final int score2Team1;
    private final int score3Team1;
    private final int score1Team2;
    private final int score2Team2;
    private final int score3Team2;
    private final Referee mainReferee;
    private final List<Referee> referees;
    private final List<BallBoy> ballBoys;
    
    public Game(int id, int date, int time, int courtIndex, int roundIndex, Player player1, Player player2, Player player3, Player player4, int score1Team1, int score2Team1, int score3Team1, int score1Team2, int score2Team2, int score3Team2, Referee mainReferee, List<Referee> referees, List<BallBoy> ballBoys){
        this.id = id;
        this.time = time;
        this.date = date;
        this.courtIndex = courtIndex;
        this.roundIndex = roundIndex;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.score1Team1 = score1Team1;
        this.score2Team1 = score2Team1;
        this.score3Team1 = score3Team1;
        this.score1Team2 = score1Team2;
        this.score2Team2 = score2Team2;
        this.score3Team2 = score3Team2;
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
    
    public int getRoundIndex(){
        return roundIndex;
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
    
    public int getScore1Team1(){
        return score1Team1;
    }
    
    public int getScore2Team1(){
        return score2Team1;
    }
    
    public int getScore3Team1(){
        return score3Team1;
    }
    
    public int getScore1Team2(){
        return score1Team2;
    }
    
    public int getScore2Team2(){
        return score2Team2;
    }
    
    public int getScore3Team2(){
        return score3Team2;
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

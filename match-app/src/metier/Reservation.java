package metier;

public class Reservation {
    private final int id;
    private final int date;
    private final int time;
    private final int courtIndex;
    private final Player player;
    
    public Reservation(int id, int date, int time, int courtIndex, Player player) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.courtIndex = courtIndex;
        this.player = player;
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
    
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Reservation){
            return id == ((Reservation) other).getId();
        }
        
        return false;
    }

    public Object toDisplayString() {
        return String.format("Réservé par %s", player.toDisplayString());
    }
}

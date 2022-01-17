package metier;

public class Player extends Person {
    private final String nationality;
    
    public Player(int id, String firstName, String name, String nationality) {
        super(id, firstName, name);
        
        this.nationality = nationality;
    }
    
    public String getNationality(){
        return nationality;
    }
}

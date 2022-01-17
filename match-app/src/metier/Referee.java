package metier;

public class Referee extends Person {
    private final String nationality;
    private final String level;
    
    public Referee(int id, String firstName, String name, String nationality, String level) {
        super(id, firstName, name);
        
        this.nationality = nationality;
        this.level = level;
    }
    
    public String getNationality(){
        return nationality;
    }
    
    public String getLevel(){
        return level;
    }
}

package metier;

public class Referee {
    private final int id;
    private final String firstName;
    private final String name;
    private final String nationality;
    private final String level;
    
    public Referee(int id, String firstName, String name, String nationality, String level) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.nationality = nationality;
        this.level = level;
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getName(){
        return name;
    }
    
    public String getNationality(){
        return nationality;
    }
    
    public String getLevel(){
        return level;
    }
    
    public String toDisplayString(){
        return String.format("%s %s", firstName, name.toUpperCase());
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Referee){
            return id == ((Referee) other).getId();
        }
        
        return false;
    }
}

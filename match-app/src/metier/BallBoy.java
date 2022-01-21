package metier;

public class BallBoy {
    private int id;
    private String firstName;
    private String name;
    
    public BallBoy(int id, String firstName, String name) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
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
    
    public String toDisplayString(){
        return String.format("%s %s", firstName, name.toUpperCase());
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof BallBoy){
            return id == ((BallBoy) other).getId();
        }
        
        return false;
    }
}

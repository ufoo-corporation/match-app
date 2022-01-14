package metier;

public class Person {
    private int id;
    private String firstName;
    private String name;
    
    public Person(int id, String firstName, String name){
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return String.format("id: %d, %s %s", id, firstName, name.toUpperCase());
    }
}

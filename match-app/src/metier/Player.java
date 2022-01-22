package metier;

public class Player {
    private int id;
    private String firstName;
    private String name;
    private String nationality;
    private String login;
    private String password;
    
    public Player(int id, String firstName, String name, String nationality, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.nationality = nationality;
        this.login = login;
        this.password = password;
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
    
    public String getLogin(){
        return login;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String toDisplayString(){
        return String.format("%s %s", firstName, name.toUpperCase());
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Player){
            return id == ((Player) other).getId();
        }
        
        return false;
    }
}

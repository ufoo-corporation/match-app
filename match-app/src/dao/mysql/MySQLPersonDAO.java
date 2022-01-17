package dao.mysql;

import dao.IPersonDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.BallBoy;
import metier.Person;
import metier.Player;
import metier.Referee;

public class MySQLPersonDAO implements IPersonDAO {
    private Connection connection;
    
    @Override
    public List<Person> getPeople() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Person> result = new ArrayList<>();
        
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM people;");

            while(resultSet.next()){
                result.add(fromDB(resultSet));
            }
        } catch (SQLException ex){
            System.out.println(ex);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

                if (resultSet != null){
                    resultSet.close(); 
                }
            } catch (SQLException ex) { 
                System.out.println(ex);
            } 
        }
        
        return result;
    }

    @Override
    public void createPerson(Person person) {
        PreparedStatement statement = null;
        
        String type = null;
        String firstName = person.getFirstName();
        String name = person.getName();
        String email = null;
        String password = null;
        String nationality = null;
        String level = null;
        
        if(person instanceof BallBoy){
            BallBoy ballboy = (BallBoy) person;
            
            type = "BALLBOY";
        }else if(person instanceof Player){
            Player player = (Player) person;
            
            type = "PLAYER";
            nationality = player.getNationality();
        }else{
            Referee referee = (Referee) person;
            
            type = "REFEREE";
            nationality = referee.getNationality();
            level = referee.getLevel();
        }
        
        try{
            String instruction = "INSERT INTO people(id, type, first_name, name, email, password, nationality, level) VALUES (NULL,?,?,?,?,?,?,?);";
            statement = connection.prepareStatement(instruction);
            
            statement.setString(1, type);
            statement.setString(2, firstName);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, nationality);
            statement.setString(7, level);
            
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException ex) { 
                System.out.println(ex);
            } 
        }
    }

    @Override
    public void deletePerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private Person fromDB(ResultSet resultSet){
        try{
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String firstName = resultSet.getString("first_name");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String nationality = resultSet.getString("nationality");
            String level = resultSet.getString("level");

            switch(type){
                case "BALLBOY":
                    return new BallBoy(id, firstName, name);
                case "PLAYER":
                    return new Player(id, firstName, name, nationality);
                case "REFEREE":
                    return new Referee(id, firstName, name, nationality, level);
                default:
                    System.out.printf("The type '%s' does not exist%n", type);
                    break;
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
}

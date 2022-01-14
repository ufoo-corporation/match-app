package dao.mysql;

import dao.IPersonDAO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.Person;

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
                result.add(new Person(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("name")));
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
        
        try{
            String instruction = "INSERT INTO people(id, first_name, name) VALUES (NULL,?,?);";
            statement = connection.prepareStatement(instruction);
            
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getName());
            
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
}

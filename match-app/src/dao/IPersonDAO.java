package dao;

import java.util.List;
import metier.Person;
import java.sql.Connection;

public interface IPersonDAO {
    public List<Person> getPeople();
    
    public void createPerson(Person person);
    
    public void deletePerson(int id);
    
    public void setConnection(Connection connexion);
}

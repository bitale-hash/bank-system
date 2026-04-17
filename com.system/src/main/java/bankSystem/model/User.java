package bankSystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String id;

    private String name;

    public User() {}

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
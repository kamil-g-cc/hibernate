package cool.code;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "USERS")
public class User {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(generator = "increment")
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public User(String name){
        this.name = name;
    }
    public User(){

    }
}

package Model;

import java.io.Serializable;
//implement model class of Users
public class Users implements Serializable{
    private String name;
    private String email;
    private String username;
    private String password;
    private String type;
//Constuctor of the class
    public Users(String name, String email, String username, String password, String type) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }
//getters and setters of the class
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Users{" + "name=" + name + ", email=" + email + ", username=" + username + ", password=" + password + ", type=" + type + '}';
    }
   
}

/*
implement DataAccess Class which handle with the file for getting data from
and writing data into file
*/ 
package issuetracker;
import Model.Issues;
import Model.Users;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataAccess implements Serializable {
    //implement method which add a new user into file
    public void addUser(String name, String email, String username, String password, String type) {
        ArrayList<Users> user = null;
        try {
            user = getUsers();
            user.add(new Users(name, email, username, password, type));
            FileOutputStream fout = new FileOutputStream("users.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(user);
            out.flush();    
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // method of getting all the users from file
    public ArrayList getUsers() {
        ArrayList<Users> users = null;
        try {
            FileInputStream fis = new FileInputStream("users.txt");
            int fileCheck = fis.available();
            if (fileCheck != 0) {
                ObjectInputStream in = new ObjectInputStream(fis);
                users = (ArrayList<Users>) in.readObject();
                in.close();
            } else {
                users = new ArrayList<Users>();
            }
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }
    // method of adding a new issue in file
    public void addIssue(String name, String env, String type, String details, String status, String assignTo) {
        try {
            ArrayList<Issues> issues = getIssues();
            issues.add(new Issues(issues.size()+1, name, env, type, details, status, assignTo));
            FileOutputStream fout = new FileOutputStream("issues.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(issues);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // method of getting all issues from file
    public ArrayList getIssues() {
        ArrayList<Issues> issues = null;
        try {
            FileInputStream fis = new FileInputStream("issues.txt");
            int fileCheck = fis.available();
            if (fileCheck != 0) {
                ObjectInputStream in = new ObjectInputStream(fis);
                issues = (ArrayList<Issues>) in.readObject();
                in.close();
            } else {
                issues = new ArrayList<Issues>();
            }
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return issues;
    }
    // method of updating an issue in file
    public void updateIssues(ArrayList<Issues> issues){
        try {
            FileOutputStream fout = new FileOutputStream("issues.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(issues);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

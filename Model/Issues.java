package Model;
import java.io.Serializable;
// implement model class of issues
public class Issues implements Serializable{
    private int id;
    private String name;
    private String env;
    private String type;
    private String details;
    private String status;
    private String assignTo;
//constructor of the class
    public Issues(int id, String product, String env, String type, String details, String status, String assginTo) {
        this.id = id;
        this.name = product;
        this.env = env;
        this.type = type;
        this.details = details;
        this.status = status;
        this.assignTo = assignTo;
    }
//constructor of the class
    public Issues(String product, String env, String type, String details, String status, String assginTo) {
        this.name = product;
        this.env = env;
        this.type = type;
        this.details = details;
        this.status = status;
        this.assignTo = assignTo;
    }
// getters and setters of the class
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String product) {
        this.name = product;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issues{" + "id=" + id + ", name=" + name + ", env=" + env + ", type=" + type + ", details=" + details + ", status=" + status + ", assignTo=" + assignTo + '}';
    }
  
}

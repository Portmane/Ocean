package frameworks.hibernate.firstHibernateProgram;


import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Employee {
    @Id
    private int id;
    private String username;
    private String password;
    private String user_email;
    private String first_name;
    private String last_name;
    public Employee() {};  //Standard object.
    public Employee(String username, String password, String user_email) {
        this.username = username;
        this.password = password;
        this.user_email = user_email;
    }
    public Employee(String username, String password, String user_email, String first_name, String last_name) {
        this(username, password, user_email);
        this.first_name = first_name;
        this.last_name = last_name;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}

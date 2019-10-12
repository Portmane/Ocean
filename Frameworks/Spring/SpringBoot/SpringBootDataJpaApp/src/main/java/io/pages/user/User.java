package io.pages.user;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}


    @Column(name = "username")
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_email")
    private String user_email;
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Column(name = "first_name")
    private String first_name;
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    private String last_name;
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }



    public User() {};  //Standard object.
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
    public User(String id, String username, String password) {
        this(id, username);
        this.password = password;
    }
    public User(String id, String username, String password, String user_email, String first_name, String last_name) {
        this(id, username, password);
        this.first_name = user_email;
        this.last_name = first_name;
        this.last_name = last_name;
    }
}
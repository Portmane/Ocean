package io.pages.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "group_id")
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {this.id = id;}


    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    private String description;
    public String getdesciption() {
        return description;
    }
    public void setdesciption(String password) {
        this.description = password;
    }


    public Group() {};  //Standard object.
    public Group(String id, String name, String description) {
        this.id = id;
        this.name = this.name;
        this.description = description;
    }
}
package io.domain;

import javax.persistence.*;
import java.util.Set;

@Entity                         // Marks that this is databases table template.
@Table(name = "usr")            // How would table be named. / To what table this entity have to be mapped.
public class User {
    @Id                             // Primary key.
    @GeneratedValue(strategy = GenerationType.AUTO) // How would this primary ket be maneged.
    private Long id;                // Variable which stores the ID value.

    private String username;            // Variable which stores the name value.
    private String password;        // Variable which stores the password value.
    private boolean active;         // Variable which stores the state of the User.



    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

package io.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String picture;
    private String email;
    private String gender;
    private String locale;

    @Column(name = "last_visit")
    private LocalDateTime lastVisit;
}
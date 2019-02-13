package com.obido.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(schema = "PUBLIC", name = "JOGGING_USER")
@Getter
@Setter
public class User {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Jog> jogEntities;
    @Id
    @SequenceGenerator(name = "jogging_seq", sequenceName = "public.jogging_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jogging_seq")
    @Column(name = "id", unique = true, nullable = false, precision = 9)
    private Long id;
    @Column(name = "login", length = 100)
    private String login;
    @Column(name = "password", length = 80)
    private String password;

    public User(Long id) {
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
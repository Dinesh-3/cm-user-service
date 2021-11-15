package com.dinesh.UserService.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Log extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
    private int status;

    public Log(String path, int status) {
        this.path = path;
        this.status = status;
    }

}

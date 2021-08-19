package com.dinesh.UserService.entity;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long student_id;
    private long department_id;
    private String path;
    private int status;

    public Log() {
    }

    public Log(long student_id, long department_id, String path, int status) {
        this.student_id = student_id;
        this.department_id = department_id;
        this.path = path;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }
}

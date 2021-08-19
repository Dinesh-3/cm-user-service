package com.dinesh.UserService.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_log")
public class UserLog extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int status;
    private long student_id;
    private long department_id;

    public UserLog() {
    }

    public UserLog(int status, long student_id, long department_id) {
        this.status = status;
        this.student_id = student_id;
        this.department_id = department_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }
}

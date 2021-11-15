package com.dinesh.UserService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_log")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserLog extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int status;
    private long student_id;
    private long department_id;

    public UserLog(int status, long student_id, long department_id) {
        this.status = status;
        this.student_id = student_id;
        this.department_id = department_id;
    }

}

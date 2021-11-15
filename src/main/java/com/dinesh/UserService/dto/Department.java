package com.dinesh.UserService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Department{
    private long id;
    private String name;
    private Instant created_at;
    private Instant updated_at;
}

package com.dinesh.UserService.repository;

import com.dinesh.UserService.entity.UserLog;
import org.springframework.data.repository.CrudRepository;

public interface UserLogRepository extends CrudRepository<UserLog, Long> {
}

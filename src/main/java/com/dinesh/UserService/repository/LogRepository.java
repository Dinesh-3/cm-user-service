package com.dinesh.UserService.repository;

import com.dinesh.UserService.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}

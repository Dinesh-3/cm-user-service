package com.dinesh.UserService.service;

import com.dinesh.UserService.dto.Department;
import com.dinesh.UserService.entity.Log;
import com.dinesh.UserService.entity.User;
import com.dinesh.UserService.repository.LogRepository;
import com.dinesh.UserService.repository.UserRepository;
import com.dinesh.UserService.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<ResponseBody> getAll() {
        Iterable<User> users = repository.findAll();
        ResponseBody responseBody = new ResponseBody(users);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getById(long id) {
        Optional<User> user = repository.findById(id);
        ResponseBody response = restTemplate.getForObject("http://department-service/departments/"+user.get().getDepartment_id(), ResponseBody.class);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("department", response.getData());

        ResponseBody responseBody = new ResponseBody(result);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> create(User user) {
        User savedUser = repository.save(user);
        ResponseBody responseBody = new ResponseBody(savedUser);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> delete(long id) {
        repository.deleteById(id);
        ResponseBody responseBody = new ResponseBody(null);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getLogs() {
        Iterable<Log> logs = logRepository.findAll();
        return new ResponseEntity<>(new ResponseBody(logs), HttpStatus.OK);
    }

    public void insertLog(Log log) {
        logRepository.save(log);
    }
}

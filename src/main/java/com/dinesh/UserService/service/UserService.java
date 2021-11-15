package com.dinesh.UserService.service;

import com.dinesh.UserService.dto.Department;
import com.dinesh.UserService.entity.Log;
import com.dinesh.UserService.entity.User;
import com.dinesh.UserService.entity.UserLog;
import com.dinesh.UserService.exception.UserNotFoundException;
import com.dinesh.UserService.repository.LogRepository;
import com.dinesh.UserService.repository.UserLogRepository;
import com.dinesh.UserService.repository.UserRepository;
import com.dinesh.UserService.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    private final String DEP_TOPIC = "quickstart-events";

    private final UserRepository repository;
    private final LogRepository logRepository;
    private final UserLogRepository userLogRepository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate kafkaTemplate;

    public UserService(UserRepository repository, LogRepository logRepository, UserLogRepository userLogRepository, RestTemplate restTemplate, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.logRepository = logRepository;
        this.userLogRepository = userLogRepository;
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<ResponseBody> getAll() {
        Iterable<User> users = repository.findAll();
        ResponseBody responseBody = new ResponseBody(users);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getById(long id) {
        User user = repository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User ID: %d Not Found", id))
                );
        ResponseBody response = restTemplate.getForObject("http://department-service/departments/"+user.getDepartment_id(), ResponseBody.class);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("department", response.getData());

        ResponseEntity<ResponseBody> responseEntity = restTemplate.getForEntity("http://department-service/departments/" + user.getDepartment_id(), ResponseBody.class);
        System.out.println("responseEntity.getStatusCode() = " + responseEntity.getStatusCode());
        System.out.println("responseEntity.getHeaders() = " + responseEntity.getHeaders());
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());

        CompletableFuture.supplyAsync(() -> kafkaTemplate.send(DEP_TOPIC, String.format("Recently Fetched User: %s", user)));
        ResponseBody responseBody = new ResponseBody(result);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> create(final User user) {
        User savedUser = repository.save(user);
        ResponseBody responseBody = new ResponseBody(savedUser);
        kafkaTemplate.send(DEP_TOPIC, String.format("New User: %s %s was recently Added", user.getFirst_name(), user.getLast_name()));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> delete(long id) {
        repository.deleteById(id);
        ResponseBody responseBody = new ResponseBody(null);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getLogs() {
        Iterable<UserLog> logs = userLogRepository.findAll();
        return new ResponseEntity<>(new ResponseBody(logs), HttpStatus.OK);
    }

    public void insertLog(Log log) {
        logRepository.save(log);
    }
}

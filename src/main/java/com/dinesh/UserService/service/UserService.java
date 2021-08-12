package com.dinesh.UserService.service;

import com.dinesh.UserService.entity.User;
import com.dinesh.UserService.repository.UserRepository;
import com.dinesh.UserService.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public ResponseEntity<ResponseBody> getAll() {
        Iterable<User> users = repository.findAll();
        ResponseBody responseBody = new ResponseBody(users);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity<ResponseBody> getById(long id) {
        Optional<User> user = repository.findById(id);
        ResponseBody responseBody = new ResponseBody(user);
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
}

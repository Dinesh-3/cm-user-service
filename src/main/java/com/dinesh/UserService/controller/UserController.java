package com.dinesh.UserService.controller;

import com.dinesh.UserService.entity.User;
import com.dinesh.UserService.service.UserService;
import com.dinesh.UserService.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    ResponseEntity<ResponseBody> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseBody> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping()
    ResponseEntity<ResponseBody> create(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping()
    ResponseEntity<ResponseBody> update(@RequestBody User user) {
        return service.create(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseBody> delete(@PathVariable long id) {
        return service.delete(id);
    }

}

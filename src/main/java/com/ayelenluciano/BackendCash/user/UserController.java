package com.ayelenluciano.BackendCash.user;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Pageable;
import com.ayelenluciano.BackendCash.user.*;

@RestController
public class UserController {

  @Autowired
  UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/users")
  List<User> all() {
    return repository.findAll();
  }

  @GetMapping("/users/{id}")
  ResponseEntity<User> one(@PathVariable Long id) {
    try{
      Optional<User> user_optional = repository.findById(id);
      User user = user_optional.get();
      return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }
    catch(NoSuchElementException noElement){
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/users")
  User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }



  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
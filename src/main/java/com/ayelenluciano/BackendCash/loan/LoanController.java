package com.ayelenluciano.BackendCash.loan;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.NoSuchElementException;

import com.ayelenluciano.BackendCash.loan.*;
import com.ayelenluciano.BackendCash.user.User;
import com.ayelenluciano.BackendCash.user.UserRepository;

@RestController
class LoanController {

  @Autowired
  LoanRepository repository;

  @Autowired
  UserRepository repository_user;


  LoanController(LoanRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/loans")
  ResponseEntity<Page<Loan>> allorByUserId(@RequestParam (value="userId",required = false) Long userId, @RequestParam (name="size", required = true) Integer size, @RequestParam (name="page", required = true) Integer page ) {
      Pageable pageable = PageRequest.of(page, size);

      
      if(userId != null){
        try{
          Optional<User> user_optional = repository_user.findById(userId);
          User user = user_optional.get();
          repository.findByUserId(user, pageable);
          return new ResponseEntity<Page<Loan>>(repository.findByUserId(user, pageable), HttpStatus.OK);
        }
        catch(NoSuchElementException noElement){
          return new ResponseEntity<Page<Loan>>(HttpStatus.NOT_FOUND);
        }
      } 
      return new ResponseEntity<Page<Loan>>(repository.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/loans/{id}")
  ResponseEntity<Loan> one(@PathVariable Long id) {
    try{
      Optional<Loan> loan_optional = repository.findById(id);
      Loan loan = loan_optional.get();
      return new ResponseEntity<Loan>(loan, HttpStatus.NOT_FOUND);
    }
    catch(NoSuchElementException noElement){
      return new ResponseEntity<Loan>(HttpStatus.NOT_FOUND);
    }
    
  }

  @PostMapping("/loans")
  Loan newLoan(@RequestParam (value = "userId", required = true) Long userId, @RequestBody Loan newLoan) {
    Optional<User> user_optional = repository_user.findById(userId);
    User user = user_optional.get();
    newLoan.setUserId(user);
    return repository.save(newLoan);
  }

  @DeleteMapping("/loans/{id}")
  void deleteLoan(@PathVariable Long id) {
    repository.deleteById(id);
  }



}
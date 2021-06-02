package com.ayelenluciano.BackendCash.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.*;

import com.ayelenluciano.BackendCash.loan.Loan;

@Entity
@Table(name = "users")
public class User {

  @Id 
  @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
  @OneToMany(cascade=CascadeType.REMOVE, mappedBy="userId") 
  private List<Loan> loans;

    public User(){}

    public User(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.loans = new ArrayList<Loan>();
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Long return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return List return the loans
     */
    public List<Loan> getLoans() {
        return loans;
    }

}
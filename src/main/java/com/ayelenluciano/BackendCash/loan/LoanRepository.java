package com.ayelenluciano.BackendCash.loan;
import com.ayelenluciano.BackendCash.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ayelenluciano.BackendCash.user.User;

@Repository
interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findAll(Pageable pageable);
    Page<Loan> findByUserId(User user, Pageable pageable);
}
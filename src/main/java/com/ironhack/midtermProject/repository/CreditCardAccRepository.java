package com.ironhack.midtermProject.repository;
import com.ironhack.midtermProject.model.CreditCardAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAccRepository extends JpaRepository<CreditCardAcc, Long> {
}

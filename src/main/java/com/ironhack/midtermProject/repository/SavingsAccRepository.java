package com.ironhack.midtermProject.repository;
import com.ironhack.midtermProject.model.SavingsAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccRepository extends JpaRepository<SavingsAcc, Long> {
}

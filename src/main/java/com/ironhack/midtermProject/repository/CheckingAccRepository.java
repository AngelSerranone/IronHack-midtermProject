package com.ironhack.midtermProject.repository;
import com.ironhack.midtermProject.model.CheckingAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccRepository extends JpaRepository<CheckingAcc, Long> {
}

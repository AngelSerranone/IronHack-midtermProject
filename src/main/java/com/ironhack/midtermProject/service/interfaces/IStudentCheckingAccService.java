package com.ironhack.midtermProject.service.interfaces;

import com.ironhack.midtermProject.model.StudentCheckingAcc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IStudentCheckingAccService{

    /** Method to find all student checking accounts (you have to be and ADMIN) **/
    public List<StudentCheckingAcc> showAll();

    /** Method to find one student checking account by id (you have to be and ADMIN) **/
    public Optional<StudentCheckingAcc> find(Long id);
}

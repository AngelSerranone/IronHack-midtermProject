package com.ironhack.midtermProject.service.impl;

import com.ironhack.midtermProject.model.SavingsAcc;
import com.ironhack.midtermProject.model.StudentCheckingAcc;
import com.ironhack.midtermProject.repository.SavingsAccRepository;
import com.ironhack.midtermProject.repository.StudentCheckingAccRepository;
import com.ironhack.midtermProject.service.interfaces.IStudentCheckingAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCheckingAccService implements IStudentCheckingAccService {

    @Autowired
    private StudentCheckingAccRepository studentCheckingAccRepository;

    /** Method to find all savings accounts (you have to be and ADMIN) **/
    public List<StudentCheckingAcc> showAll(){
        if (studentCheckingAccRepository.findAll().size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No checking accounts found");
        }
        return studentCheckingAccRepository.findAll();
    }

    /** Method to find one savings account by id (you have to be and ADMIN) **/
    public Optional<StudentCheckingAcc> find(Long id){
        if (studentCheckingAccRepository.findById(id).isPresent()){
            return studentCheckingAccRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no account with the provided id");
        }
    }
}

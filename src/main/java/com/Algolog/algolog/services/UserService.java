package com.Algolog.algolog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Algolog.algolog.entities.User;
import com.Algolog.algolog.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user){
        User createdUser = this.userRepo.save(user);
        return createdUser;
    }

    public List<User> getAllUsers(){
        List<User> allUsers = this.userRepo.findAll();
        return allUsers;
    }

    public User getUserById(Integer id){
        User user = this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return user;
    }

}


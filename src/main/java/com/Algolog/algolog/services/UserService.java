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

    public User updateUser(Integer id, User user){
        User newUser = this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());

        this.userRepo.save(newUser);

        return newUser;
    }

    public void deleteUser(Integer id){
        this.userRepo.deleteById(id);
    }

}


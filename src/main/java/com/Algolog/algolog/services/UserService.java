package com.Algolog.algolog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Algolog.algolog.entities.Leaderboard;
import com.Algolog.algolog.entities.Post;
import com.Algolog.algolog.entities.User;
import com.Algolog.algolog.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user){
        User createdUser = this.userRepo.save(user);
        createdUser.setTotalScore(0);
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

    public List<Leaderboard> getLeaderboard() {
        List<User> users = this.userRepo.findAll();
        List<Leaderboard> leaderboard = new ArrayList<>();
        
        for(User user : users){
            int easy = 0, medium = 0, hard = 0;

            for(Post post : user.getPosts()){
                switch (post.getDifficulty().toLowerCase()) {
                    case "easy" -> easy++;
                    case "medium" -> medium++;
                    case "hard" -> hard++;
                }
            }

            leaderboard.add(new Leaderboard(
                0,  // temp rank
                user.getId(),
                user.getName(),
                easy,
                medium,
                hard,
                user.getTotalScore()
            ));
        }

        leaderboard.sort((a, b) -> Integer.compare(b.getTotalScore(), a.getTotalScore()));

        // Assign rank
        for (int i = 0; i < leaderboard.size(); i++) {
            leaderboard.get(i).setRank(i + 1);
        }

        return leaderboard;
    }

}


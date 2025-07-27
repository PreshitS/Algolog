package com.Algolog.algolog.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Algolog.algolog.entities.Post;
import com.Algolog.algolog.entities.User;
import com.Algolog.algolog.repositories.PostRepo;
import com.Algolog.algolog.repositories.UserRepo;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired 
    private UserRepo userRepo;

    private final Map<String, Integer> scoreMap = Map.of("Easy", 1, "Medium", 2, "Hard", 3);


    public Post createPost(Integer userId, Post post){

        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        post.setUser(user);
        int scoreOfDifficulty = scoreMap.get(post.getDifficulty());
        int curUserScore = user.getTotalScore();
        post.setScore(scoreOfDifficulty);
        user.setTotalScore(curUserScore + scoreOfDifficulty);
        userRepo.save(user);
        Post createdPost = this.postRepo.save(post);
        return createdPost;
    }

    public List<Post> getAllPosts(){
        List<Post> allPosts = this.postRepo.findAll();
        return allPosts;
    }

    public Post getPostById(Integer id){
        Post post = this.postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return post;
    }

    public Post updatePost(Integer id, Post post){
        Post newPost = this.postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        User user = newPost.getUser();
        user.setTotalScore(user.getTotalScore() - newPost.getScore());

        newPost.setTitle(post.getTitle());
        newPost.setDifficulty(post.getDifficulty());
        newPost.setTestcases(post.getTestcases());
        newPost.setDescription(post.getDescription());
        
        int newScore = scoreMap.get(post.getDifficulty());
        newPost.setScore(newScore);
        user.setTotalScore(user.getTotalScore() + newScore);

        this.userRepo.save(user);
        
        this.postRepo.save(newPost);

        return newPost;
    }

    public void deletePost(Integer id){
        Post deletedPost = this.postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        User user = deletedPost.getUser();
        user.setTotalScore(user.getTotalScore() - deletedPost.getScore());
        this.userRepo.save(user);
        this.postRepo.deleteById(id);
    }

}

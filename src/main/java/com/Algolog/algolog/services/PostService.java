package com.Algolog.algolog.services;

import java.util.List;

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

    public Post createPost(Integer id, Post post){

        User user = userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        post.setUser(user);
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
        newPost.setTitle(post.getTitle());
        newPost.setDifficulty(post.getDifficulty());
        newPost.setTestcases(post.getTestcases());
        newPost.setDescription(post.getDescription());
        
        this.postRepo.save(newPost);

        return newPost;
    }

    public void deletePost(Integer id){
        this.postRepo.deleteById(id);
    }

}

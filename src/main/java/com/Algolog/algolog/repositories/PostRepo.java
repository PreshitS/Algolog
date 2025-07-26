package com.Algolog.algolog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Algolog.algolog.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}

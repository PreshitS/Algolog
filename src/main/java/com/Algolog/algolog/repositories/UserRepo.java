package com.Algolog.algolog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Algolog.algolog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}

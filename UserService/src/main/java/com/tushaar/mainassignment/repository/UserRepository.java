package com.tushaar.mainassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushaar.mainassignment.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

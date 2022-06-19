package com.tushaar.mainassignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushaar.mainassignment.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByAddress(String string);

	Optional<User> findByMobile(String mobile);

}

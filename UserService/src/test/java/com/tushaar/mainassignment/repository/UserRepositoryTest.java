package com.tushaar.mainassignment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tushaar.mainassignment.models.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	private User user;

	@BeforeEach
	public void setUp() {
		user = new User("Tushaar", "Punjab", "8968980024", new Date(), "123");
	}

	@AfterEach
	public void tearDown() {
		try {
			repository.deleteAll();
			user = null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void addUser() {
		repository.save(user);
		User u = repository.findById(user.getId()).get();
		assertEquals(1L, u.getId());
	}
	
	@Test
	public void findByAddress() {
		User u2 = new User("Dummy", "Punjab", "9876543210", new Date(), "1234");
		repository.save(user);
		repository.save(u2);
		List<User> usersList = repository.findByAddress(user.getAddress());
		assertEquals(2, usersList.size());
	}
	
	@Test
	public void deleteById() {
		User savedUser = repository.save(user);
		Long userId = savedUser.getId();
		repository.deleteById(userId);
		Optional<User> u = repository.findById(userId);
		assertEquals(Optional.empty(), u);
	}
}

package com.tushaar.mainassignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository repository;

	@Autowired
	@InjectMocks
	private UserService service;

	private User user1;
	private User user2;
	List<User> userList;

	@BeforeEach
	public void setUp() {
		userList = new ArrayList<>();
		user1 = new User(1L, "Tushaar", "Punjab", "8968980024", new Date(), "123");
		user2 = new User(2L, "Dummy", "Punjab", "9876543210", new Date(), "1234");
		userList.add(user1);
		userList.add(user2);
	}

	@AfterEach
	public void tearDown() {
		user1 = user2 = null;
		userList = null;
	}

	@Test
	void testCreateUser() {
		when(repository.save(any())).thenReturn(user1);
		service.createUser(user1);
		verify(repository, times(1)).save(any());
	}

	@Test
	void testFindUserById() {
		//Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(user1));
		assertThat(service.findUserById(new HashMap<String, String>(), user1.getId()).getBody()).isEqualTo("Unauthorized");
	}

	@Test
	void testDeleteUserById() {
		when(repository.save(any())).thenReturn(user1);
		service.createUser(user1);
		assertThat(service.findUserById(new HashMap<String, String>(), user1.getId()).getBody()).isEqualTo("Unauthorized");
	}
}

package com.tushaar.mainassignment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tushaar.mainassignment.models.User;
import com.tushaar.mainassignment.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService service;
	private User user, updatedUserInput, updatedUserOutput, createdUser;
	private List<User> userList;

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@BeforeEach
	public void setup() {
		user = new User("Tushaar", "Punjab", "8968980024", new Date());
		createdUser = new User(1L, user.getName(), user.getAddress(), user.getMobile(), user.getCreatedAt());
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@AfterEach
	void tearDown() {
		user = null;
	}

	@Test
	void testCreateUser() throws Exception {
		when(service.createUser(any())).thenReturn(new ResponseEntity<Object>(createdUser, HttpStatus.CREATED));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated());
		verify(service, times(1)).createUser(any());
	}

	@Test
	void testFindUserById() throws Exception {
		when(service.findUserById(createdUser.getId()))
				.thenReturn(new ResponseEntity<Object>("No user found", HttpStatus.BAD_REQUEST));
		mockMvc.perform(get("/users/" + createdUser.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user))).andExpect(status().isBadRequest());
	}

	@Test
	void testFindUserByIdWhenIdDoesNotExist() throws Exception {
		when(service.findUserById(0L)).thenReturn(new ResponseEntity<Object>(createdUser, HttpStatus.OK));
		mockMvc.perform(get("/users/" + 0L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteUserById() throws Exception {
		when(service.deleteUserById(createdUser.getId()))
				.thenReturn(new ResponseEntity<Object>("User deleted", HttpStatus.OK));
		mockMvc.perform(delete("/users/" + createdUser.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user))).andExpect(status().isOk());
	}

	@Test
	void testDeleteUserByIdWhenIdDoesNotExist() throws Exception {
		when(service.deleteUserById(0L))
				.thenReturn(new ResponseEntity<Object>("No user found", HttpStatus.BAD_REQUEST));
		mockMvc.perform(delete("/users/" + 0L).contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isBadRequest());
	}
}

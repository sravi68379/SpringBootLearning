package com.ravi.RestTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private Map <Long, User> userStore= new HashMap<>();
	private Long idCounter = 1L;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		user.setId(idCounter++);
		userStore.put(user.getId(), user);
		return user;
	}
	@GetMapping
	public Collection<User> getAllUser(){
		return userStore.values();
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userStore.get(id);
	}
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		userStore.put(id, user);
		return user;
	}
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userStore.remove(id);
		return "User Removed";
	}
	
}

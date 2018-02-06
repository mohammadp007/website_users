package com.example.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userprofile.UserProfile;

@RestController
public class UserController {
	@Autowired 
	private UserRepository userRepository;
	
	//user controlling
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		int x = Integer.parseInt(id);
		User u1 = userRepository.findOne(x);
		if(u1 == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(u1);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	   userRepository.save(user);
	   return ResponseEntity.ok().build();
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") String id, 
	                                       @RequestBody User newUser) {
		int x = Integer.parseInt(id);
		User User = userRepository.findOne(x);
	    if(User == null) {
	        return ResponseEntity.notFound().build();
	    }
	    User.setName(newUser.getName());
	    User.setEmail(newUser.getEmail());

	    User updatedUser = userRepository.save(User);
	    return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") String id) {
		int x = Integer.parseInt(id);
		User user = userRepository.findOne(x);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    userRepository.delete(user);
	    return ResponseEntity.ok().build();
	}
	
	//user profile controlling
	
	@GetMapping("/users/{id}/profile")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable String id) {
		int x = Integer.parseInt(id);
		User u1 = userRepository.findOne(x);
		if(u1 == null) {
	        return ResponseEntity.notFound().build();
	    }
		UserProfile profile = u1.getUserProfile();
		if(profile == null) {
			return ResponseEntity.notFound().build();
		}
	    return ResponseEntity.ok().body(profile);
	}
	
	@PostMapping("/users/{id}/profile")
	public ResponseEntity<UserProfile> createUserProfile(@PathVariable String id,
												  @RequestBody UserProfile userprofile) {
		int x = Integer.parseInt(id);
		User u1 = userRepository.findOne(x);
		if(u1 == null) {
	        return ResponseEntity.notFound().build();
	    }
		u1.addUserProfile(userprofile);
		userRepository.save(u1);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/users/{id}/profile")
	public ResponseEntity<UserProfile> updateUserProfile(@PathVariable(value = "id") String id, 
														 @RequestBody UserProfile userProfile) {
		int x = Integer.parseInt(id);
		User User = userRepository.findOne(x);
	    if(User == null) {
	        return ResponseEntity.notFound().build();
	    }
	    User.setUserProfile(userProfile);
	    userRepository.save(User);
	    return ResponseEntity.ok(userProfile);
	}
	
	@DeleteMapping("/users/{id}/profile")
	public ResponseEntity<UserProfile> deleteUserProfile(@PathVariable(value = "id") String id) {
		int x = Integer.parseInt(id);
		User user = userRepository.findOne(x);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    user.deleteProfile();
	    userRepository.save(user);
	    return ResponseEntity.ok().build();
	}
}

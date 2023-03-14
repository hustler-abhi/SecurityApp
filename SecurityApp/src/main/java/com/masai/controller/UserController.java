package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dao.UserDao;
import com.masai.entity.User;

@RestController
public class UserController {

	@Autowired
	private UserDao udao;

	@Autowired
	private PasswordEncoder encd ;

	@GetMapping("/msg")
	public String getmsg() {
		return "welcome to spring security!";
	}

	@GetMapping("/signIn")
	public ResponseEntity<User> getLoggedInCustomerDetailsHandler(Authentication auth) {

		User customer = udao.findByEmail(auth.getName());

		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAlluser() {
		return new ResponseEntity<List<User>>(udao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getoneUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<User>(udao.findById(id).get(), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveuser(@RequestBody User user) {
		user.setPassword(encd.encode(user.getPassword()));
		return new ResponseEntity<User>(udao.save(user), HttpStatus.OK);
	}

	@DeleteMapping("/users")
	public ResponseEntity<User> deleteuser(@RequestParam Integer id) {
		User use = udao.findById(id).get();
		udao.delete(use);
		return new ResponseEntity<User>(use, HttpStatus.OK);
	}

}

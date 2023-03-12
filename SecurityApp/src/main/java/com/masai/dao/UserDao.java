package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.User;

public interface UserDao extends  JpaRepository<User,Integer>{
	
	public User findByEmail(String email);
 
}

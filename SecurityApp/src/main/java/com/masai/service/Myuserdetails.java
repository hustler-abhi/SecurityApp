package com.masai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.dao.UserDao;
import com.masai.entity.User;

@Service
public class Myuserdetails implements UserDetailsService {

	@Autowired
	private UserDao udao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = udao.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("bad creadintial");
		} else {

			List<GrantedAuthority> auth = new ArrayList<>();
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), auth);
		}

	}

}

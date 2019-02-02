package com.session.library.libraryofbooks.configuration;

import com.session.library.libraryofbooks.model.User;
import com.session.library.libraryofbooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findOneByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("fail");
		}
		else
		return new CustomUserDetails(user);
	}

	public User getUser(Integer id) {

		return this.userRepo.findOneById(id);
	}

}

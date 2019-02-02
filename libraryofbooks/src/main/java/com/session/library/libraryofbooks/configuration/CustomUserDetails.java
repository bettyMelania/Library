package com.session.library.libraryofbooks.configuration;

import java.util.ArrayList;
import java.util.Collection;

import com.session.library.libraryofbooks.model.Role;
import com.session.library.libraryofbooks.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class CustomUserDetails extends User implements UserDetails{
	private boolean isAccountNonExpired=true;
	
	public CustomUserDetails(final User user) {
		super(user);

	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		Collection<Role> roles= super.getRoles();
		for(Role r:roles){
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
			authorities.add(authority);
			}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

package com.fazz.library.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fazz.library.model.entity.User;
import com.fazz.library.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    Optional<User> userFind = userRepository.findByEmail(username);
    if (userFind.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    User user = userFind.get();
    return UserDetailsImpl.build(user);
  }

}

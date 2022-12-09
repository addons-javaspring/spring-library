package com.fazz.library.service.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fazz.library.model.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private Boolean isDeleted;

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");

    return new UserDetailsImpl(user.getEmail(), user.getPassword(), authorities, user.getIsDeleted());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return this.authorities;
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return this.password;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return !false;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return !false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return !false;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return this.isDeleted;
  }

}

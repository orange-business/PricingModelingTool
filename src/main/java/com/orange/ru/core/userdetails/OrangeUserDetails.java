package com.orange.ru.core.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

/* Пользователь OrangeUserDetails с одной стороны - это наш OrangeUser, с другой стороны - класс пользователя Spring Security. */
public class OrangeUserDetails extends OrangeUser implements UserDetails {
  private List<GrantedAuthority> authorities;
  public OrangeUserDetails(OrangeUser user, List<GrantedAuthority> authorities) {
    this.setId(user.getId());
    this.setEmail(user.getEmail());
    this.setFirstName(user.getFirstName());
    this.setLastName(user.getLastName());
    this.setPassword(user.getPassword());
    this.setStatus(user.getStatus());
    this.authorities = authorities;
  }
  public Collection<GrantedAuthority> getAuthorities() { return authorities; }
  public String getUsername() { return getEmail(); }
  public boolean isAccountNonExpired() { return getStatus(); }
  public boolean isAccountNonLocked() {
    return getStatus();
  }
  public boolean isCredentialsNonExpired() { return getStatus(); }
  public boolean isEnabled() { return getStatus(); }
  private static final long serialVersionUID = 3384436451564509032L;
}
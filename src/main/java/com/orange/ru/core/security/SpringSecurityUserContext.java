package com.orange.ru.core.security;

import java.util.*;
import com.orange.ru.core.userdetails.OrangeUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * An implementation of {@link UserContext} that looks up the {@link OrangeUser} using the Spring Security's
 * {@link Authentication} by principal name.
 */
@Component
public class SpringSecurityUserContext implements UserContext {
  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }
  private String authoritiesByUsernameQuery = "select group_role.role_name from (select b.group_id, b.role_name from (select g.id as group_id, r.role as role_name from groups g, group_roles gr, roles r where g.id = gr.group_id and gr.role_id = r.id) b) group_role, (select a.group_id, a.email from ( select g.id as group_id, u.email as email from groups g, group_members gm, users u where g.id = gm.group_id and gm.user_id = u.id) a) group_email where group_email.group_id = group_role.group_id and group_email.email = ?";

  public OrangeUser getCurrentUser() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    if (authentication == null) return null;
    return (OrangeUser) authentication.getPrincipal();
  }
  public void setCurrentUser(OrangeUser user) {
    if (user == null) throw new IllegalArgumentException("user cannot be null");
    Collection<? extends GrantedAuthority> authorities = getAuthoritiesByUsername(user.getEmail());
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(user, user.getPassword(), (Collection<GrantedAuthority>) authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
  // Пользовательский метод
  private List<GrantedAuthority> getAuthoritiesByUsername(String pEmail){
    List<String> roles = em.createNativeQuery(authoritiesByUsernameQuery).setParameter(1, pEmail).getResultList();
    if (roles.size() == 0) throw new UsernameNotFoundException("Username {0} have no roles", pEmail);
    return getGrantedAuthorities(roles);
  }
  private List<GrantedAuthority> getGrantedAuthorities(final List<String> roles){
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (final String role: roles){
      GrantedAuthority authority = new GrantedAuthority(){ public String getAuthority() { return role; } };
      authorities.add(authority);
    }
    return authorities;
  }
}
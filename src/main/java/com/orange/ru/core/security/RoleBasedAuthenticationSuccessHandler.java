package com.orange.ru.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
/**
 * В зависимости от роли и группы мы будем перебрасывать (после авторизации) пользователя на его страницу.
 * Например, залогинившись как администратор, вы будете переблошены на страницу администрирования.
 * User: Зайнуллин Радик
 * Date: 08.04.13
 */
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private Map<String, String> roleUrlMap;

  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                    Authentication authentication) throws IOException, ServletException {
    if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      Collection<? extends GrantedAuthority> authenticationCollection = userDetails.getAuthorities();
      if (authenticationCollection.size()==0) throw new UsernameNotFoundException("No roles found for user {0}", userDetails.getUsername());
      Iterator it = authenticationCollection.iterator();
      GrantedAuthority authority  = (GrantedAuthority) it.next();
      String role = authority.getAuthority();
      response.sendRedirect(request.getContextPath() + roleUrlMap.get(role));
    }
  }
  public void setRoleUrlMap(Map<String, String> roleUrlMap) {
    this.roleUrlMap = roleUrlMap;
  }
}
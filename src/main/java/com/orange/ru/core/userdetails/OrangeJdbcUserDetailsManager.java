package com.orange.ru.core.userdetails;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Repository
public class OrangeJdbcUserDetailsManager implements UserDetailsService, UserDetailsManager, GroupManager, Serializable {
  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }

  private String userByUsernameQuery = "select id,firstName,lastName,password,status from users where email=?";
  private String authoritiesByUsernameQuery = "select group_role.role_name from (select b.group_id, b.role_name from (select g.id as group_id, r.role as role_name from groups g, group_roles gr, roles r where g.id = gr.group_id and gr.role_id = r.id) b) group_role, (select a.group_id, a.email from ( select g.id as group_id, u.email as email from groups g, group_members gm, users u where g.id = gm.group_id and gm.user_id = u.id) a) group_email where group_email.group_id = group_role.group_id and group_email.email = ?";
  private String authoritiesByGroupNameQuery = "select r.role from groups g, group_roles gr, roles r where g.id = gr.group_id and gr.role_id = r.id and g.group_name = ?";
  private String findAllGroupsQuery = "select group_name from groups";
  private String findUsersInGroupQuery = "select u.email from users u, group_members gm, groups g where u.id = gm.user_id and g.id = gm.group_id and g.group_name = ?";
  private String userExistsQuery = "select count(*) from users u where u.email = ?";
  private String addUserToGroupQuery = "insert into group_members(user_id, group_id) select u.id, g.id from groups g, users u where u.email = ? and g.group_name = ?";

  public UserDetails loadUserByUsername(String pEmail) throws UsernameNotFoundException, DataAccessException {
    List<Object[]> users = em.createNativeQuery(userByUsernameQuery).setParameter(1, pEmail).getResultList();
    if (users.size() == 0) throw new UsernameNotFoundException("Username {0} not found", pEmail);
    Object[] u = users.get(0);
    OrangeUserBuilder orangeUserBuilder = new OrangeUserBuilder().id(u[0]).firstName(u[1]).lastName(u[2]).password(u[3]).status(u[4]).email(pEmail);
    return new OrangeUserDetails(orangeUserBuilder.assembleProduct(), getAuthoritiesByUsername(pEmail));
  }

  public List<GrantedAuthority> findGroupAuthorities(String groupName) {
    List<String> roles = em.createNativeQuery(authoritiesByGroupNameQuery).setParameter(1, groupName).getResultList();
    if (roles.size() == 0) throw new UsernameNotFoundException("No roles found for group {0}", groupName);
    return getGrantedAuthorities(roles);
  }
  public void createUser(UserDetails userDetails) {}

  public List<String> findAllGroups() { return em.createNativeQuery(findAllGroupsQuery).getResultList(); }

  public List<String> findUsersInGroup(String groupName) {
    return em.createNativeQuery(findUsersInGroupQuery).setParameter(1, groupName).getResultList();
  }
  public void createGroup(String groupName, List<GrantedAuthority> grantedAuthorities) { }
  public void deleteGroup(String groupName) {}
  public void renameGroup(String oldName, String newName) {}
  public void addUserToGroup(String email, String groupName) {
    em.createNativeQuery(addUserToGroupQuery).setParameter(1, email).setParameter(2, groupName).executeUpdate();
  }
  public void removeUserFromGroup(String userName, String groupName) {}
  public void addGroupAuthority(String groupName, GrantedAuthority grantedAuthority) {}
  public void removeGroupAuthority(String s, GrantedAuthority grantedAuthority) { }
  public void updateUser(UserDetails userDetails) {}
  public void deleteUser(String email) {}
  // поменять пароль у текущего пользователя
  public void changePassword(String oldPassword, String newPassword) {}
  public boolean userExists(String email) {
    int num = Integer.parseInt((String) em.createNativeQuery(userExistsQuery).setParameter(1, email).getSingleResult());
    return (num == 0)? false:true;
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
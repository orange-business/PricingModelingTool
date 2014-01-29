package com.orange.ru.service;

import com.orange.ru.core.userdetails.OrangeJdbcUserDetailsManager;
import com.orange.ru.core.userdetails.OrangeUser;
import com.orange.ru.swf.model.OrangeUserForm;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.PersistenceContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import javax.persistence.EntityManager;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
/**
 * Администратор: Работа с пользователями
 * User: Зайнуллин Радик
 * Date: 10.04.13
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
  private String addOrangeUserQuery = "insert into users(email, firstName, lastName, password) values(?,?,?,?)";
  private String isEmailOccupiedQuery = "select count(*) from users where email=?";
  private String deleteOrangeUserQuery = "delete from users where email=?";
  private String getUserIdByEmailQuery = "select id from users where email=?";

  @Autowired
  private OrangeJdbcUserDetailsManager orangeJdbcUserDetailsManager;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AdminServiceImpl(PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
  }

  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }

  @Transactional(readOnly = true)
  public List<String> initializeSelectableGroups(){
    return orangeJdbcUserDetailsManager.findAllGroups();
  }

  public Event validateGroupSelected(OrangeUserForm orangeUser, MessageContext messageContext){
    List<String> list = orangeUser.getSelectedGroups();
    if (list == null || list.size()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("selectedGroups");
      errorMessageBuilder.code("error.page.selectedGroups.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    } else return new EventFactorySupport().success(this);
  }

  public Event validateFirstName(OrangeUserForm orangeUser, MessageContext messageContext){
    String firstName = orangeUser.getFirstName();
    if (firstName == null || firstName.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("firstName");
      errorMessageBuilder.code("error.page.firstName.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }
  public Event validateLastName(OrangeUserForm orangeUser, MessageContext messageContext){
    String lastName = orangeUser.getLastName();
    if (lastName == null || lastName.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("lastName");
      errorMessageBuilder.code("error.page.lastName.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }
  public Event validatePassword(OrangeUserForm orangeUser, MessageContext messageContext){
    String password = orangeUser.getPassword();
    if (password == null || password.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("password");
      errorMessageBuilder.code("error.page.password.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    if (password.length() < 5) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("password");
      errorMessageBuilder.code("error.page.password.length.too.short");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }
  @Transactional
  public Event validateEmail(OrangeUserForm orangeUser, MessageContext messageContext){
    String email = orangeUser.getEmail();
    if (email == null || email.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("email");
      errorMessageBuilder.code("error.page.email.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    if (!email.endsWith("@orange.com")) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("email");
      errorMessageBuilder.code("error.page.email.orange.domain.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    BigDecimal count = (BigDecimal) em.createNativeQuery(isEmailOccupiedQuery).setParameter(1, email).getSingleResult();
    if (count.equals(new BigInteger(String.valueOf(1)))){
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("email");
      errorMessageBuilder.code("error.page.email.orange.occupied");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }

  @Transactional
  public Event isEmailExists(OrangeUserForm orangeUser, MessageContext messageContext){
    String email = orangeUser.getEmail();
    BigDecimal count = (BigDecimal) em.createNativeQuery(isEmailOccupiedQuery).setParameter(1, email).getSingleResult();
    if (count.equals(new BigInteger(String.valueOf(0)))){
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("email");
      errorMessageBuilder.code("error.page.email.orange.notExists");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);  
  }  
  @Transactional
  public void persistOrangeUser(OrangeUserForm orangeUser) throws UnsupportedEncodingException, NoSuchAlgorithmException{
    String email = orangeUser.getEmail();
    String firstName = orangeUser.getFirstName();
    String lastName = orangeUser.getLastName();
    String password = orangeUser.getPassword();

    String encodedPassword = passwordEncoder.encode(password);
    em.createNativeQuery(addOrangeUserQuery).setParameter(1, email).setParameter(2, firstName)
        .setParameter(3, lastName)
        .setParameter(4, encodedPassword).executeUpdate();
    em.flush();
    List<String> selectedGroups = orangeUser.getSelectedGroups();
    for (String groupName: selectedGroups){
      orangeJdbcUserDetailsManager.addUserToGroup(email, groupName);
    }
  }

  @Override
  public OrangeUser persistOrangeUser(OrangeUser orangeUser) {
    String email = orangeUser.getEmail();
    String firstName = orangeUser.getFirstName();
    String lastName = orangeUser.getLastName();
    String password = orangeUser.getPassword();

    String encodedPassword = passwordEncoder.encode(password);
    em.createNativeQuery(addOrangeUserQuery).setParameter(1, email).setParameter(2, firstName)
        .setParameter(3, lastName)
        .setParameter(4, encodedPassword).executeUpdate();
    em.flush();
    List<String> selectedGroups = orangeUser.getSelectedGroups();
    for (String groupName: selectedGroups){
      orangeJdbcUserDetailsManager.addUserToGroup(email, groupName);
    }
    Long id = (Long) em.createNativeQuery(getUserIdByEmailQuery).setParameter(1, email).getSingleResult();
    return em.find(OrangeUser.class, id);
  }

  @Transactional
  public void deleteOrangeUser(OrangeUserForm orangeUser){
    String email = orangeUser.getEmail();
    em.createNativeQuery(deleteOrangeUserQuery).setParameter(1, email).executeUpdate();
  }
  public String getEmail(Principal currentUser){
    return currentUser.getName();
  }
}
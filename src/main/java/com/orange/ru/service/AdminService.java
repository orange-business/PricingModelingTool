package com.orange.ru.service;

import com.orange.ru.core.userdetails.OrangeUser;
import com.orange.ru.swf.model.OrangeUserForm;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.Event;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.List;
/**
 * Сервис для цепочки администрирования пользователей.
 * User: Зайнуллин Радик
 * Date: 10.04.13
 */
public interface AdminService {
  public List<String> initializeSelectableGroups();
  public void persistOrangeUser(OrangeUserForm orangeUser) throws UnsupportedEncodingException, NoSuchAlgorithmException;
  public OrangeUser persistOrangeUser(OrangeUser orangeUser);
  public Event validateGroupSelected(OrangeUserForm orangeUser, MessageContext messageContext);
  public Event validateEmail(OrangeUserForm orangeUser, MessageContext messageContext);
  public Event validateFirstName(OrangeUserForm orangeUser, MessageContext messageContext);
  public Event validateLastName(OrangeUserForm orangeUser, MessageContext messageContext);
  public Event validatePassword(OrangeUserForm orangeUser, MessageContext messageContext);
  public Event isEmailExists(OrangeUserForm orangeUser, MessageContext messageContext);
  public void deleteOrangeUser(OrangeUserForm orangeUser);
  public String getEmail(Principal currentUser);
}

package com.orange.ru.swf.model;

import java.io.Serializable;
import java.util.List;
/**
 * {@link com.orange.ru.swf.model.OrangeUserForm} is this applications notion of a user. It is good to use your own objects to interact with a
 * user especially in large applications. This ensures that as you evolve your security requirements (update Spring
 * Security, leverage new Spring Security modules, or even swap out security implementations) you can do so easily.
 * User: Зайнуллин Радик
 * Date: 10.04.13
 */
public class OrangeUserForm implements Serializable {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Boolean status;
  private List<String> selectedGroups;

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public List<String> getSelectedGroups() {
    return selectedGroups;
  }
  public void setSelectedGroups(List<String> selectedGroups) {
    this.selectedGroups = selectedGroups;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }
}
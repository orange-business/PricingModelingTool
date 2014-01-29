package com.orange.ru.core.userdetails;

import org.codehaus.jackson.annotate.JsonIgnore;
import java.io.Serializable;
import java.util.List;
/**
 * {@link OrangeUser} is this applications notion of a user. It is good to use your own objects to interact with a
 * user especially in large applications. This ensures that as you evolve your security requirements (update Spring
 * Security, leverage new Spring Security modules, or even swap out security implementations) you can do so easily.
 * User: Зайнуллин Радик
 * Date: 10.04.13
 */
public class OrangeUser implements Serializable {
  private Long id;
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
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  /** Gets the id for this user. When creating a new user this should be null, otherwise it will be non-null. */
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
// --- convenience methods ---
  @JsonIgnore
  public String getName() {
    return getLastName() + ", " + getFirstName();
  }
  // --- override Object ---
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    OrangeUser other = (OrangeUser) obj;
    if (id == null) { if (other.id != null) return false; }
    else if (!id.equals(other.id)) return false;
    return true;
  }
  private static final long serialVersionUID = 8433999509932007961L;
}
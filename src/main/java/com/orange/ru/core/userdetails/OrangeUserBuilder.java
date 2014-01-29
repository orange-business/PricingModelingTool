package com.orange.ru.core.userdetails;

import java.util.List;

public class OrangeUserBuilder {
  private OrangeUser product;

  public OrangeUserBuilder(){
    initProduct();
  }
  void initProduct() {
    this.product = new OrangeUser();
  }
  public OrangeUserBuilder id(Object id) {
    if (id instanceof String) this.id((String) id);
    if (id instanceof Integer) this.id((Integer) id);
    if (id instanceof Long) this.id((Long) id);
    return this;
  }  
  public OrangeUserBuilder id(String id) {
    product.setId(new Long(id));
    return this;
  }
  public OrangeUserBuilder id(Integer id) {
    product.setId(new Long(id));
    return this;
  }
  public OrangeUserBuilder id(Long id) {
    product.setId(id);
    return this;
  }

  public OrangeUserBuilder firstName(Object firstName) {
    this.firstName((String)firstName);
    return this;
  }
  public OrangeUserBuilder firstName(String firstName) {
    product.setFirstName(firstName);
    return this;
  }
  public OrangeUserBuilder lastName(Object lastName) {
    this.lastName((String) lastName);
    return this;
  }
  public OrangeUserBuilder lastName(String lastName) {
    product.setLastName(lastName);
    return this;
  }
  public OrangeUserBuilder password(Object password) {
    this.password((String) password);
    return this;
  }
  public OrangeUserBuilder password(String password) {
    product.setPassword(password);
    return this;
  }
  public OrangeUserBuilder email(String email) {
    product.setEmail(email);
    return this;
  }
  public OrangeUserBuilder status(Object status) {
    if (status instanceof String) this.status((String) status);
    if (status instanceof Boolean) this.status((Boolean) status);
    if (status instanceof Integer) this.status((Integer) status);
    if (status instanceof Character) this.status((Character) status);
    return this;
  }
  public OrangeUserBuilder status(String status) {
    if (status.equals("true")||status.equals("1")) product.setStatus(true);
    else product.setStatus(false);
    return this;
  }
  public OrangeUserBuilder status(Character status) {
    if (status.equals('1')) product.setStatus(true);
    else product.setStatus(false);
    return this;
  }

  public OrangeUserBuilder status(Boolean status) {
    product.setStatus(status);
    return this;
  }
  public OrangeUserBuilder status(Integer status) {
    if (status.equals(1)) product.setStatus(true);
    else product.setStatus(false);
    return this;
  }

  public OrangeUserBuilder selectedGroups(List<String> selectedGroups) {
    product.setSelectedGroups(selectedGroups);
    return this;
  }
  public OrangeUser assembleProduct() {
    return this.product;
  }
}
package com.orange.ru.swf.model;

import com.orange.ru.domain.*;
import com.orange.ru.domain.product.Product;
import com.orange.ru.domain.product.ProductItem;

import java.io.Serializable;
import java.util.*;

public class OrderForm implements Serializable {
  private Long orderId;
  private Long orderDetailsId;
  private Long clientId;
  private String opportunityId;
  private String note;
  private String selectedLocationTown;
  public String getSelectedLocationTown() { return selectedLocationTown; }
  public void setSelectedLocationTown(String selectedLocationTown) { this.selectedLocationTown = selectedLocationTown; }

  private Customer selectedClient;
  private Opportunity selectedOrder;
  private Product provision;
  private Scenario selectedOrderDetails;
  private List<ProductItem> provisionItemList;


  public OrderForm(){
    provisionItemList = new ArrayList<ProductItem>();
  }
  public Customer getSelectedClient() {
    return selectedClient;
  }
  public void setSelectedClient(Customer client) {
    selectedClient = client;
  }
  public Opportunity getSelectedOrder() {
    return selectedOrder;
  }
  public void setSelectedOrder(Opportunity selectedOrder) {
    this.selectedOrder = selectedOrder;
  }
  public Scenario getSelectedOrderDetails() {
    return selectedOrderDetails;
  }
  public void setSelectedOrderDetails(Scenario selectedOrderDetails) {
    this.selectedOrderDetails = selectedOrderDetails;
  }
  public List<ProductItem> getProvisionItemList() {
    return provisionItemList;
  }
  public void addProvisionItem(ProductItem provisionItem){
    this.provisionItemList.add(provisionItem);
  }

  public String getOpportunityId() {
    return opportunityId;
  }

  public void setOpportunityId(String opportunityId) {
    this.opportunityId = opportunityId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getOrderDetailsId() {
    return orderDetailsId;
  }

  public void setOrderDetailsId(Long orderDetailsId) {
    this.orderDetailsId = orderDetailsId;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public Product getProvision() {
    return provision;
  }

  public void setProvision(Product provision) {
    this.provision = provision;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
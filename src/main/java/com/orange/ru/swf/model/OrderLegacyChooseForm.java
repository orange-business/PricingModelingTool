package com.orange.ru.swf.model;

import com.orange.ru.domain.Opportunity;
import com.orange.ru.domain.Scenario;

import java.io.Serializable;

public class OrderLegacyChooseForm implements Serializable{
  private Opportunity selectedOrder;
  private Long clientId;
  private Long orderId;
  private Long orderDetailsId;
  private Scenario selectedOrderDetails;

  public Opportunity getSelectedOrder() {
    return selectedOrder;
  }
  public void setSelectedOrder(Opportunity selectedOrder) {
    this.selectedOrder = selectedOrder;
  }
  public Long getClientId() {
    return clientId;
  }
  public void setClientId(Long clientId) {
    this.clientId = clientId;
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
  public Scenario getSelectedOrderDetails() {
    return selectedOrderDetails;
  }
  public void setSelectedOrderDetails(Scenario selectedOrderDetails) {
    this.selectedOrderDetails = selectedOrderDetails;
  }
}
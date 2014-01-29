package com.orange.ru.service;

import com.orange.ru.domain.*;
import com.orange.ru.swf.model.OrderForm;
import org.joda.time.LocalDateTime;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Service("opportunityService")
@Repository
@Transactional(readOnly = false)
public class OpportunityServiceImpl implements OpportunityService {
  private EntityManager em;

  @PersistenceContext
  public void setEntityManager(EntityManager em) {
    this.em = em;
  }
  @Transactional(readOnly = true)
  public Opportunity findById(long id) {
    return em.find(Opportunity.class, id);
  }
  @Transactional(readOnly = false)
  public void deleteById(long id) {
    Opportunity order = em.find(Opportunity.class, id);
    em.remove(order);
  }
  @Transactional(readOnly = false)
  public Long store(Opportunity order) {
    Opportunity order1 = em.merge(order);
    return order1.getId();
  }
  @Override
  @Transactional(readOnly = true)
  public List<Opportunity> findByClient(Customer client) {
    return em.createNamedQuery("Opportunity.findAllByClient").setParameter(1, client).getResultList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Opportunity> findByClient(Long clientId) {
    Customer client = em.find(Customer.class, clientId);
    return em.createNamedQuery("Opportunity.findAllByClient").setParameter(1, client).getResultList();
  }

  @Override
  @Transactional(readOnly = false)
  public Long saveOrUpdate(Customer client, Long orderId, String opportunityId, Long orderDetailsId) {
    Opportunity order = null;
    if (orderId.equals(Long.parseLong("-1"))) {
      // Создаем новый заказ
      order = new Opportunity();
      order.setCustomer(client);
      order.setExternalId(opportunityId);
      order.setCreationDate(new LocalDateTime());
      order.setClosed(false);
      Scenario orderDetails = em.find(Scenario.class, orderDetailsId);
      order.getScenarios().add(orderDetails);
      em.persist(order);
      return order.getId();
    } else{
      order = em.find(Opportunity.class, orderId);
      order.setExternalId(opportunityId);
      Scenario orderDetails = em.find(Scenario.class, orderDetailsId);
      order.getScenarios().add(orderDetails);
    }
    em.flush();
    return order.getId();
  }

  @Override
  public Event validateOpportunityId(OrderForm form, MessageContext messageContext) {
    String opportunityId = form.getOpportunityId();
    if (opportunityId == null || opportunityId.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("opportunityId");
      errorMessageBuilder.code("error.page.opportunityId.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    if (!opportunityId.matches("\\d+")){
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("opportunityId");
      errorMessageBuilder.code("error.page.opportunityId.isNotNumber");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }

  @Override
  public Event validateNote(OrderForm form, MessageContext messageContext) {
    String note = form.getNote();
    if (note == null || note.length()==0) {
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("note");
      errorMessageBuilder.code("error.page.note.required");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }
  @Override
  public Event validateClient(Long clientId, MessageContext messageContext){
    Customer client = em.find(Customer.class, clientId);
    List<Opportunity> orders = em.createNamedQuery("Opportunity.findAllByClient").setParameter(1, client).getResultList();
    if (orders.size()==0){
      MessageBuilder errorMessageBuilder = new MessageBuilder().error();
      errorMessageBuilder.source("orderId");
      errorMessageBuilder.code("error.page.orderId.ordersNotExists");
      messageContext.addMessage(errorMessageBuilder.build());
      return new EventFactorySupport().error(this);
    }
    return new EventFactorySupport().success(this);
  }

  @Override
  public Long findByDetailsId(Long orderDetailsId) {
    List<Opportunity> orderList = em.createQuery("select c from Opportunity c").getResultList();
    for (Opportunity order:orderList){
      Set<Scenario> orderDetailsList = order.getScenarios();
      for(Scenario orderDetails: orderDetailsList){
        if (orderDetails.getId().equals(orderDetailsId)) return order.getId();
      }
    }
    return Long.parseLong("-1");
  }
}
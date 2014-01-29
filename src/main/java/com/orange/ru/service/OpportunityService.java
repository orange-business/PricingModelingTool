package com.orange.ru.service;

import com.orange.ru.domain.Customer;
import com.orange.ru.domain.Opportunity;
import com.orange.ru.swf.model.OrderForm;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.Event;
import java.util.List;

public interface OpportunityService {
  public Opportunity findById(long id);
  public void deleteById(long id);
  public Long store(Opportunity order);
  public List<Opportunity> findByClient(Customer client);
  public List<Opportunity> findByClient(Long clientId);
  public Long saveOrUpdate(Customer client, Long orderId, String opportunityId, Long orderDetailsId);
  public Event validateOpportunityId(OrderForm form, MessageContext messageContext);
  public Event validateNote(OrderForm form, MessageContext messageContext);
  public Event validateClient(Long clientId, MessageContext messageContext);
  public Long findByDetailsId(Long orderDetailsId);
}
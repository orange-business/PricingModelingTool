package com.orange.ru.controller;

import com.orange.ru.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

/** Обслуживание SPA-страниц. Котроллеры которые выводят на SPA страницы.
 * 1. Пользователю показываются набор пар - номер оппортьюнити, номер сценария, дата последнего редактирования сценария, и формуется ссылка
 * на соответствующий SPA.
 */
@Controller
public class SpaController {
  @Autowired
  private OpportunityService opportunityService;
  public OpportunityService getOpportunityService() { return opportunityService; }
  public void setOpportunityService(OpportunityService ce) { this.opportunityService = ce; }

  @RequestMapping(value = "/scenarios/select", method = RequestMethod.GET)
  public ModelAndView scenariosEdit(HttpServletResponse response, Principal principal){
    Map map = opportunityService.findByUserScenariousInfoMap(principal);
    return new ModelAndView("scenarios/select/list", "map", map);
  }
}
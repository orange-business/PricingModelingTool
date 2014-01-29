package com.orange.ru.controller;

import com.orange.ru.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Обслуживание кодов
 * User: radik
 * Date: 12.06.13
 */
@Controller
public class CodeController {
  @Autowired private IdentificationService identification;
  public void setIdentification(IdentificationService identification) { this.identification = identification; }

  @RequestMapping(value = "/public/static/code", method = RequestMethod.GET)
  public String showCodes(Model model) throws Exception {
    model.addAttribute("codes", identification.getAll());
    return "public/static/code";
  }
}

package com.orange.ru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/test")
public class TestController {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String testString(@PathVariable Long id){
    return "Hello " + id;
  }
}
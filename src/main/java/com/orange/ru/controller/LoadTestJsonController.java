package com.orange.ru.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@Controller
@RequestMapping(value="/product")
public class LoadTestJsonController {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String getProductAsJson(@PathVariable Long id){
    InputStream inputStream = this.getClass().getResourceAsStream("/heap/waste.json");
    StringWriter writer = new StringWriter();
    try {
      IOUtils.copy(inputStream, writer, "UTF-8");
    } catch (IOException e) {  e.printStackTrace();  }
    String json = writer.toString();
    return json;
  }
}

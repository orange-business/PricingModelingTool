package com.orange.ru.controller;

import com.orange.ru.compendium.BusinessVpnTariffsHandler;
import com.orange.ru.compendium.JsonResponse;
import com.orange.ru.controller.util.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Controller
@RequestMapping(value="/compendiumFiles")
public class UploadCompendiumFilesController {
  @Autowired
  private BusinessVpnTariffsHandler businessVpnTariffsHandler;
  public void setBusinessVpnTariffsHandler(BusinessVpnTariffsHandler bvpnTariffsHandler) {
    this.businessVpnTariffsHandler = bvpnTariffsHandler;
  }

  private UploadedFile ufile;
  public UploadCompendiumFilesController(){ ufile = new UploadedFile(); }

  @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
  public void get(HttpServletResponse response,@PathVariable String value){
    try {
      response.setContentType(ufile.type);
      response.setContentLength(ufile.length);
      FileCopyUtils.copy(ufile.bytes, response.getOutputStream());
    } catch (IOException e) { e.printStackTrace(); }
  }
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody JsonResponse upload(MultipartHttpServletRequest request, HttpServletResponse response) { // BindingResult result,
    //0. notice, we have used MultipartHttpServletRequest
    //1. get the files from the request object
    Iterator<String> itr =  request.getFileNames();
    JsonResponse res = new JsonResponse();

    while(itr.hasNext()){
      MultipartFile mpf = request.getFile(itr.next());
      try { //just temporary save file info into ufile
        ufile.length = mpf.getBytes().length;
        ufile.bytes= mpf.getBytes();
        ufile.type = mpf.getContentType();
        ufile.name = mpf.getOriginalFilename();
      } catch (IOException e) {
        e.printStackTrace();
      }
      // Загрузка в базу данных только BusinessVpnTariffs.csv
      if (ufile.name.equalsIgnoreCase("BusinessVpnTariffs.csv")) {
        String text = getAsString(mpf);
        businessVpnTariffsHandler.call(text);
//        res.setStatus("SUCCESS");
//        res.setResult("File is processed!");
      } else {
//        ValidationUtils.rejectIfEmpty(result, "name", "File name must be BusinessVpnTariffs.csv.");
//        res.setStatus("FAIL");
//        res.setResult(result.getAllErrors());
      }
    }

    return res;
  }

  private String getAsString(MultipartFile mpf){
    StringWriter writer = new StringWriter();
    try { IOUtils.copy(mpf.getInputStream(), writer, "UTF-8");  } catch (IOException e) { e.printStackTrace(); }
    return writer.toString();
  }
}
package com.example.menu.controller;

import com.example.menu.api.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/index")
  public Object index(){
    APIResponse res = new APIResponse();
    return res;
  }

}

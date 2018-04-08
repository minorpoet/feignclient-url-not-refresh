package pri.holysu.springcloud.issue.feignclienturlnotrefreshserveruattwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uat")
public class TestController {

  @GetMapping("/test")
  public String Test() {
    return "test from uat-222222";
  }
}

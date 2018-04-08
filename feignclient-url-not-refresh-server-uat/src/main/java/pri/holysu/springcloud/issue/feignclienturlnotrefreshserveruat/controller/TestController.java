package pri.holysu.springcloud.issue.feignclienturlnotrefreshserveruat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uat")
public class TestController {

  @GetMapping("/test")
  public String Test() {
    return "test from uat-11111";
  }
}

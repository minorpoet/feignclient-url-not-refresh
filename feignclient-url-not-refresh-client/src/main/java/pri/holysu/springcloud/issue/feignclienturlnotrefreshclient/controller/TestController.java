package pri.holysu.springcloud.issue.feignclienturlnotrefreshclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.holysu.springcloud.issue.feignclienturlnotrefreshclient.config.Config;
import pri.holysu.springcloud.issue.feignclienturlnotrefreshclient.feignclient.TestClient;

@RestController
public class TestController {
  @Autowired private TestClient testClient;

  @Autowired private Config config;

  @GetMapping("/feign")
  public String getByFeign() {
    return testClient.test();
  }

  /**
   * this shows the new config {uat.url} is fetched, but when you call getByFeign the calling target is still old one
   * @return
   */
  @GetMapping("/url")
  public String getByConfigAnno() {
    return config.getUrl();
  }
}

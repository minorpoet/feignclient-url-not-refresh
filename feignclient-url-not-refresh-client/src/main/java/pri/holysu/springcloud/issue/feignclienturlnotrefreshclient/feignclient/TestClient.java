package pri.holysu.springcloud.issue.feignclienturlnotrefreshclient.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="client", url = "${uat.url}")
public interface TestClient {

    @GetMapping("/uat/test")
    String test();
}

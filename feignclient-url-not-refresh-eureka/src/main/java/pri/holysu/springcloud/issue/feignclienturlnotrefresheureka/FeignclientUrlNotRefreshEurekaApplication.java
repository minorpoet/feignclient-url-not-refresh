package pri.holysu.springcloud.issue.feignclienturlnotrefresheureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FeignclientUrlNotRefreshEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignclientUrlNotRefreshEurekaApplication.class, args);
	}
}

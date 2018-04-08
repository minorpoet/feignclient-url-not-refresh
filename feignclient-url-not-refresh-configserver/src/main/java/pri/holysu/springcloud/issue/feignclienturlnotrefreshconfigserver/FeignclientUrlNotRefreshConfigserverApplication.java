package pri.holysu.springcloud.issue.feignclienturlnotrefreshconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class FeignclientUrlNotRefreshConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignclientUrlNotRefreshConfigserverApplication.class, args);
	}
}

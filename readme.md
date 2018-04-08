### to run this issue, you should :

1. start rabbimq ,    host: localhost, port: 5672 , username: admin, password: admin 

2. config repository , files  **feignclient-config-client-dev.properties**   see https://github.com/minorpoet/spring-cloud-config-repo

   `uat.url=localhost:8088 `

3. the config server application.yml is as below,   you can change depending on you own pc env

   ```
   spring:
     application:
       name: config-server
     cloud:
       config:
         fail-fast: true
         server:
           git:
             clone-on-start: true
             uri: git@github.com:minorpoet/spring-cloud-config-repo.git
     rabbitmq:
       host: localhost
       port: 5672
       username: admin
       password: admin
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8761/eureka
     instance:
       prefer-ip-address: true
       instance-id: ${spring.application.name}:${server.port}
   server:
     port: 8080
   management:
     security:
       enabled: false
   ```

   ### run order

   1. first you should run service register ***feignclient-url-not-refresh-eureka***
   2. then config server ***feignclient-url-not-refresh-configserver***
   3. start two service providers  ***feignclient-url-not-refresh-server-uat*** and ***feignclient-url-not-refresh-server-uat-two***
   4. start the client *** feignclient-url-not-refresh-client***

   ### reproduce the issue

   config that can be refreshed on the fly

   ```java
   @Component
   @ConfigurationProperties(prefix = "uat")
   public class Config {

       private String url;

       public String getUrl() {
           return url;
       }

       public void setUrl(String url) {
           this.url = url;
       }
   }
   ```

   and @FeignClient(url="${uat.url}")  that cannot be refreshed on the fly

   ```java
   @FeignClient(name="client", url = "${uat.url}")
   public interface TestClient {

       @GetMapping("/uat/test")
       String test();
   }
   ```

   #### test

   ```java
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
   ```

   visit:        http://127.0.0.1:9999/url 

   output:   localhost:**8088**

   visit:        http://127.0.0.1:9999/feign

   output:   test from uat-**11111**

   ### but 

   when i update `uat.url=localhost:8088 ` to `uat.url=localhost:8098 `  , ie  uat1 -> uat2

   and `curl -X POST http://localhost:8080/bus/refresh `

   ​

   visit:        http://127.0.0.1:9999/url 

   output:   localhost:**8089 **                            *@ConfigurationProperties config is refreshed*            

   ​

   visit:        http://127.0.0.1:9999/feign

   output:   test from uat-**11111**                  *but the url in FeignClient is old*    , i want it be refreshed too

   ​

these two uat servers are just  kind of case that i want to change url in @FeignClient,  and  the my main target is to use feign as a convenient httpclient to call old php or others (like .net) server that cannot register to eureka.
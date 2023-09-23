package work.hdjava.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@LoadBalancerClients(value = {
//        @LoadBalancerClient(name = "auth-server",configuration = MyLoadBalancerConfig.class),
//        @LoadBalancerClient(name = "user-server",configuration = MyLoadBalancerConfig.class)
//})
public class SampleGatewayServer {

//    @Bean
//    public RestTemplate restTemplate(){
//
//        return new RestTemplate();
//    }

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SampleGatewayServer.class, args);

    }
}

package work.hdjava.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SampleAuthServer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SampleAuthServer.class, args);
        PasswordEncoder passwordEncoder = run.getBean("passwordEncoder", PasswordEncoder.class);
        String encode = passwordEncoder.encode("secret");
        System.out.println(encode);

        ClientSettings build = ClientSettings.builder().requireAuthorizationConsent(true).build();
        System.out.println("111");
    }
}

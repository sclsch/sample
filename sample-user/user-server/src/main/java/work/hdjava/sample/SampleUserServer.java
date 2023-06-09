package work.hdjava.sample;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "work.hdjava.sample.user.mapper")
public class SampleUserServer {
    public static void main(String[] args) {
        SpringApplication.run(SampleUserServer.class, args);
    }
}

package server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="server.mapper")
public class ProApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProApplication.class, args);
    }
}

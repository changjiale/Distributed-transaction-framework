package consumer.server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="consumer.server.mapper")
public class ConApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConApplication.class, args);
    }
}

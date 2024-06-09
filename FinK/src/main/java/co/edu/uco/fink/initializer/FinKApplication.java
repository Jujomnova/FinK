package co.edu.uco.fink.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.fink"})
public class FinKApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinKApplication.class, args);
    }
}

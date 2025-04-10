package project.paypass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaypassApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaypassApplication.class, args);
	}

}

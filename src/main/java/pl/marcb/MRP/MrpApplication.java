package pl.marcb.MRP;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConditionalOnClass(Flyway.class)
@ComponentScan("pl.marcb")
@EnableScheduling
public class MrpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrpApplication.class, args);
	}
}

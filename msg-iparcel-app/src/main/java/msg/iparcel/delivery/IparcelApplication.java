package msg.iparcel.delivery;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration()
@SpringBootApplication
@ComponentScan(basePackages = { "msg.iparcel" })
@EntityScan(basePackages = { "domain.iparcel" })
@EnableJpaRepositories(basePackages = { "msg.iparcel" })
public class IparcelApplication {

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, SchedulerException {

		SpringApplication.run(IparcelApplication.class, args);

	}
}

package id.co.telkomsigma.belajarspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BelajarSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringBatchApplication.class, args);
	}
}

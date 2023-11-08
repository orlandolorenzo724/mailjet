package org.kreyzon.mailjet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MailjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailjetApplication.class, args);
	}

}

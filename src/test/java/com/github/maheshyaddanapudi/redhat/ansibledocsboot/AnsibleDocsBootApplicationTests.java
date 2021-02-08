package com.github.maheshyaddanapudi.redhat.ansibledocsboot;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.constants.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {Constants.TEST, Constants.MARIADB4J})
class AnsibleDocsBootApplicationTests {

	@Test
	public void customTests() {
		String args[] = {"--spring.output.ansi.enabled=always"};
		SpringApplication.run(AnsibleDocsBootApplication.class, args);
	}

}

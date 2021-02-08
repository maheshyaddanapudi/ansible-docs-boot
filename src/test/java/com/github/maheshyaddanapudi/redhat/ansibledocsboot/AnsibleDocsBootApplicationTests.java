package com.github.maheshyaddanapudi.redhat.ansibledocsboot;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.constants.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = {Constants.TEST, Constants.MARIADB4J})
public class AnsibleDocsBootApplicationTests {

	@Test
	public void contextLoads() {
		String args[] = {"--spring.output.ansi.enabled=always"};
		SpringApplication.run(AnsibleDocsBootApplication.class, args);
	}

}

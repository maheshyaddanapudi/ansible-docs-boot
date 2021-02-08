package com.github.maheshyaddanapudi.redhat.ansibledocsboot;

import com.github.maheshyaddanapudi.redhat.ansibledocsboot.constants.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		FlywayAutoConfiguration.class
		})
@ActiveProfiles(profiles = {Constants.TEST, Constants.MARIADB4J})
class AnsibleDocsBootApplicationTests {

	@Test
	public void customTests() {
		String args[] = {"--spring.output.ansi.enabled=always"};
		SpringApplication.run(AnsibleDocsBootApplication.class, args);
	}

}

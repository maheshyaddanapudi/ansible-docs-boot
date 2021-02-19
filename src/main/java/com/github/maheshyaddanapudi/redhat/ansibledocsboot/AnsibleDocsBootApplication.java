package com.github.maheshyaddanapudi.redhat.ansibledocsboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceHealthContributorAutoConfiguration.class, DataSourceAutoConfiguration.class, ErrorMvcAutoConfiguration.class})
@EnableCaching
@EnableTransactionManagement
public class AnsibleDocsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnsibleDocsBootApplication.class, args);
	}

}

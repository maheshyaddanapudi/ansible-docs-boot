package com.github.maheshyaddanapudi.redhat.ansibledocsboot.config.env;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
	
	private static final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);
	
	private volatile Connector connector;
	
	@Autowired(required = false)
	public MariaDB4jSpringService mariaDB4jSpringService;

	@Value("${awaitTermination:120}")
	private int awaitTermination;

	@Autowired(required = true)
	public DataSource hikariDataSource;

	@Override
	public void customize(Connector connector) {
		this.connector = connector;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {

		try{
			this.connector.pause();
		}
		catch(NullPointerException npe)
		{
			// Do Nothing
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		if(null!=this.hikariDataSource)
		{
			log.info("### Shutting Down Hikari DataSource ###");
			HikariDataSource hkDataSource = (HikariDataSource)this.hikariDataSource;

			if(hkDataSource.isRunning())
				hkDataSource.close();

			log.info("$$$ Hikari DataSource Shutdown Status : "+hkDataSource.isClosed()+" $$$");
		}

		if(null!=this.mariaDB4jSpringService)
		{
			log.info("### Shutting Down Embedded MariaDB ###");
			this.mariaDB4jSpringService.stop();
			log.info("$$$ Embedded MariaDB Shutdown Status : "+!this.mariaDB4jSpringService.isRunning()+" $$$");
		}

		try{
			Executor executor = this.connector.getProtocolHandler().getExecutor();
			if (executor instanceof ThreadPoolExecutor) {
				try {
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
					threadPoolExecutor.shutdown();
					if (!threadPoolExecutor.awaitTermination(awaitTermination, TimeUnit.SECONDS)) {
						log.warn("Conductor Boot thread pool did not shut down gracefully within " + awaitTermination
								+ " seconds. Proceeding with forceful shutdown");
					}
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				} catch(NullPointerException npe)
				{

				}
			}
		}
		catch(NullPointerException npe)
		{
			// Do Nothing
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

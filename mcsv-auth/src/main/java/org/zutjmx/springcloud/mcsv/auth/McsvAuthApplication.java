package org.zutjmx.springcloud.mcsv.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class McsvAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvAuthApplication.class, args);
	}

}

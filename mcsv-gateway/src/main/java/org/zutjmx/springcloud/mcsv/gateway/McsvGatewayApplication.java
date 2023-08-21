package org.zutjmx.springcloud.mcsv.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class McsvGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvGatewayApplication.class, args);
	}

}

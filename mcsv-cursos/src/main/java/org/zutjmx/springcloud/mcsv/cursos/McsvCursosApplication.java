package org.zutjmx.springcloud.mcsv.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class McsvCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvCursosApplication.class, args);
	}

}

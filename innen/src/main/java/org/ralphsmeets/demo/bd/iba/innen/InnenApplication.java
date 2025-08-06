package org.ralphsmeets.demo.bd.iba.innen;

import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InnenApplication {
    public static void main(String[] args) {
        SpringApplication.run(InnenApplication.class, args);
    }

}

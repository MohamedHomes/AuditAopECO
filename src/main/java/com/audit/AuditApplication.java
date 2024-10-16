package com.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AuditApplication {

    public static void main(String[] args) {
	SpringApplication.run(AuditApplication.class, args);
    }

}

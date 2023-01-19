package ch.zhaw.projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Hauptklasse für das Projekt X
 * <p>
 * Die Applikation ist stark angelehnt an das Getting Started-Beispiel hier:
 * https://spring.io/guides/gs/rest-service/
 *
 * @SpringBootApplication stellt sicher, dass diese Klasse die SpringBoot-Applikation automatisch konfiguriert und vieles mehr. Details: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-using-springbootapplication-annotation
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjectX {
    public static void main(String[] args) {
        SpringApplication.run(ProjectX.class, args);
    }
}

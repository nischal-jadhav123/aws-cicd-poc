package com.nischal.aws.ecs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
@OpenAPIDefinition(
        info = @Info(
                title = "News App",
                version = "1.0.0",
                description = "This is a News Application which uses News API internally to get the news",
                contact = @Contact(
                        name = "Nischal Jadhav H ",
                        email = "nischal.jadhav@gmail.com"
                )
        )
)
public class SpringbootDockerEcsApplication {

    @GetMapping
    public String applicationStatus() {
        return "Application is up and running !";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hi " + name + " Welcome to Nischal AWS CI/CD Example";
    }

    @GetMapping("/check")
    public String checkPipeline() {
        return "Hi PipeLine is working fine";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerEcsApplication.class, args);
    }

}

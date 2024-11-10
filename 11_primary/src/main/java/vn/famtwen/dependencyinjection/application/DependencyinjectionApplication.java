package vn.famtwen.	dependencyinjection.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"net.khoahocdulieu",
				"vn.famtwen.dependencyinjection.application",
				"vn.famtwen.dependencyinjection.rest",
				"vn.famtwen.dependencyinjection.service",
		}
)
public class DependencyinjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyinjectionApplication.class, args);
	}
}

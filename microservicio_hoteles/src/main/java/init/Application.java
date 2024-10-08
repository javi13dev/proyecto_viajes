package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.annotation.PostConstruct;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @PostConstruct
    public void init() {
        System.out.println("IP_HOST: " + System.getenv("IP_HOST"));
        System.out.println("USUARIO_BBDD: " + System.getenv("USUARIO_BBDD"));
        System.out.println("PASS_BBDD: " + System.getenv("PASS_BBDD"));
    }

}

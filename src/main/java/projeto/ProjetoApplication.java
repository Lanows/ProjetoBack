package projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projeto.models.TvSeries;
import projeto.service.TMDBService;
import projeto.utils.*;

@SpringBootApplication
public class ProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}
}

package projeto.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("api.rest.tmbd")
    public WebClient tmdbWebClient(WebClient.Builder builder){
        logger.info("Creating TMDB WebClient");

        return builder.baseUrl("https://api.themoviedb.org/3").build();
    }

    private static Logger logger = LoggerFactory.getLogger(WebClientConfig.class);
}

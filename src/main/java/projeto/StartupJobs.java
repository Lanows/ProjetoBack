package projeto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import projeto.utils.*;


@Component
public class StartupJobs implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(StartupJobs.class);

    @Autowired
    private TmdbLoader tmdbLoader;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent){
        logger.info("Loading data from The Movie Database");
//        tmdbLoader.loadTmdbData();
        logger.info("Done loading data from The Movie Database");
    }
}

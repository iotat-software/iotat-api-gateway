package cn.iotat.gateway.task;

import cn.iotat.gateway.faced.api.RouteService;
import cn.iotat.gateway.util.log.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStarterTask implements CommandLineRunner {
    private static final LoggerUtil LOG = new LoggerUtil(ApplicationStarterTask.class);

    @Autowired
    private RouteService routeService;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("api gateway init ...");
        routeService.refreshRoute();
        LOG.info("api gateway over, result is success!");
    }
}

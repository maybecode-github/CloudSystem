package net.maybemc.cloud.server;

import de.maybecode.mbcache.CacheConfig;
import de.maybecode.mbcache.config.MBCacheConfig;
import lombok.Getter;
import lombok.SneakyThrows;
import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.handler.CloudStoppingHandler;
import net.maybemc.cloud.service.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudServerApplication {

    @Getter
    private static Logger logger;

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(CloudServerApplication.class, args);
        logger = LoggerFactory.getLogger(CloudServerApplication.class);
        CacheConfig config = new MBCacheConfig("config/data.yml");
        CloudHttpClient client = new CloudHttpClient(config.getString("http.baseUrl", "localhost"),
                config.getString("api-key", "X-API-KEY"));

        ServiceProvider.registerProvider(CloudHttpClient.class, client);
        new CloudStoppingHandler().addShutdownHook(new CloudStoppingHandler.CloudHook());
    }
}

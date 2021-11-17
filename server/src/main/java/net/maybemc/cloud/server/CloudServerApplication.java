package net.maybemc.cloud.server;

import de.maybecode.mbcache.CacheConfig;
import de.maybecode.mbcache.config.MBCacheConfig;
import lombok.Getter;
import lombok.SneakyThrows;
import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CommandConsole;
import net.maybemc.cloud.server.command.CommandManager;
import net.maybemc.cloud.server.processor.AnnotationProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudServerApplication {

    @Getter
    private static CloudHttpClient client;

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(CloudServerApplication.class, args);
        new AnnotationProcessor(new CommandManager()).processAnnotations();
        CacheConfig config = new MBCacheConfig("config/data.yml");
        client = new CloudHttpClient(config.getString("http.baseUrl", "localhost"),
                config.getString("api-key", "X-API-KEY"));
        new CommandConsole().processConsole();
    }
}

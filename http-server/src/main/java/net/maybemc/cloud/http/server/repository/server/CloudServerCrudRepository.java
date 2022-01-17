package net.maybemc.cloud.http.server.repository.server;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!cloudServer")
public interface CloudServerCrudRepository extends CrudRepository<CloudServer, String> {
}

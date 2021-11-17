package net.maybemc.cloud.http.server.repository.group;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!cloudGroup")
public interface CloudGroupCrudRepository extends CrudRepository<CloudGroup, String> {
}

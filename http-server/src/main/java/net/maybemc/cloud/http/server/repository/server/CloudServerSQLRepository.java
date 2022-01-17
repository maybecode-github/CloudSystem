package net.maybemc.cloud.http.server.repository.server;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"dev", "default"})
@EntityScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.server"})
public class CloudServerSQLRepository implements ICloudServerRepository {

    @Autowired
    private CloudServerCrudRepository cloudServerCrudRepository;

    @Override
    public CloudServer save(CloudServer cloudServer) {
        return cloudServerCrudRepository.save(cloudServer);
    }

    @Override
    public List<CloudServer> fetchAll() {
        List<CloudServer> cloudGroups = new ArrayList<>();
        cloudServerCrudRepository.findAll().forEach(cloudGroups::add);
        return cloudGroups;
    }

    @Override
    public CloudServer fetch(String serverName) {
        return cloudServerCrudRepository.findById(serverName).orElse(null);
    }

    @Override
    public void delete(String serverName) {
        cloudServerCrudRepository.deleteById(serverName);
    }
}

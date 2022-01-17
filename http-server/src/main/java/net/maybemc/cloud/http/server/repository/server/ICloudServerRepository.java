package net.maybemc.cloud.http.server.repository.server;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;

import java.util.List;

public interface ICloudServerRepository {

    CloudServer save(CloudServer cloudServer) throws Exception;

    List<CloudServer> fetchAll();

    CloudServer fetch(String serverName);

    void delete(String serverName);

}

package net.maybemc.cloud.http.server.service.server;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;

import java.util.List;

public interface ICloudServerService {

    CloudServer fetchByName(String serverName);

    CloudServer save(CloudServer cloudServer) throws Exception;

    List<CloudServer> fetchAll();

    List<CloudServer> fetchFromGroup(String groupName);

    void delete(String serverName);

}

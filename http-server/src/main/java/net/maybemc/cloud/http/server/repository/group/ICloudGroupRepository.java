package net.maybemc.cloud.http.server.repository.group;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;

import java.util.List;

public interface ICloudGroupRepository {

    CloudGroup save(CloudGroup cloudGroup) throws Exception;

    List<CloudGroup> fetchAll();

    CloudGroup fetch(String groupName);

    void delete(String groupName);

}

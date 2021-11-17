package net.maybemc.cloud.http.server.service.group;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;

import java.util.List;

public interface ICloudGroupService {

    CloudGroup fetchByName(String groupName);

    CloudGroup save(CloudGroup cloudGroup);

    List<CloudGroup> fetchAll();

    void delete(String groupName);

}

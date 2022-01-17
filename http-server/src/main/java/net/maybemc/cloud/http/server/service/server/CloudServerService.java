package net.maybemc.cloud.http.server.service.server;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.http.server.repository.group.ICloudGroupRepository;
import net.maybemc.cloud.http.server.repository.server.ICloudServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.group"})
public class CloudServerService implements ICloudServerService {

    @Autowired
    private ICloudServerRepository cloudServerRepository;
    @Autowired
    private ICloudGroupRepository cloudGroupRepository;

    @Override
    @CacheEvict(value = "cloudServer", key = "#serverName")
    public CloudServer fetchByName(String serverName) {
        return cloudServerRepository.fetch(serverName);
    }

    @Override
    public CloudServer save(CloudServer cloudServer) throws Exception {
        return cloudServerRepository.save(cloudServer);
    }

    @Override
    public List<CloudServer> fetchAll() {
        return cloudServerRepository.fetchAll();
    }

    @Override
    public List<CloudServer> fetchFromGroup(String groupName) {
        CloudGroup cloudGroup = cloudGroupRepository.fetch(groupName);
        return cloudServerRepository.fetchAll().stream().filter(filter ->
                filter.getCloudGroup().equals(cloudGroup)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "cloudServer", key = "#serverName")
    public void delete(String serverName) {
        cloudServerRepository.delete(serverName);
    }
}

package net.maybemc.cloud.http.server.service.group;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.http.server.repository.group.ICloudGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.group"})
public class CloudGroupService implements ICloudGroupService {

    @Autowired
    private ICloudGroupRepository cloudGroupRepository;

    @Override
    @Cacheable(value = "cloudGroup", key = "#groupName")
    public CloudGroup fetchByName(String groupName) {
        return cloudGroupRepository.fetch(groupName);
    }

    @Override
    @SneakyThrows
    public CloudGroup save(CloudGroup cloudGroup) {
        return cloudGroupRepository.save(cloudGroup);
    }

    @Override
    @Cacheable("cloudGroups")
    public List<CloudGroup> fetchAll() {
        return cloudGroupRepository.fetchAll();
    }

    @Override
    @CacheEvict(value = "cloudGroup", key = "#groupName")
    public void delete(String groupName) {
        cloudGroupRepository.delete(groupName);
    }
}

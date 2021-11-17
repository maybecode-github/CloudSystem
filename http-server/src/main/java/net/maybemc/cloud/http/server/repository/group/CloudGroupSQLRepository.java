package net.maybemc.cloud.http.server.repository.group;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"dev", "default"})
@EntityScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.group"})
public class CloudGroupSQLRepository implements ICloudGroupRepository {

    @Autowired
    private CloudGroupCrudRepository cloudGroupCrudRepository;

    @Override
    public CloudGroup save(CloudGroup cloudGroup) {
        return cloudGroupCrudRepository.save(cloudGroup);
    }

    @Override
    public List<CloudGroup> fetchAll() {
        List<CloudGroup> cloudGroups = new ArrayList<>();
        Iterable<CloudGroup> groups = cloudGroupCrudRepository.findAll();
        groups.forEach(cloudGroups::add);
        return cloudGroups;
    }

    @Override
    public CloudGroup fetch(String groupName) {
        return cloudGroupCrudRepository.findById(groupName).orElse(new CloudGroup());
    }

    @Override
    public void delete(String groupName) {
        cloudGroupCrudRepository.deleteById(groupName);
    }
}

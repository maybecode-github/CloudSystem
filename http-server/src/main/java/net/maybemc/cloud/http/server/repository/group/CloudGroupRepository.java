package net.maybemc.cloud.http.server.repository.group;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.api.cloud.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("cloud")
@Component
@EntityScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.group"})
public class CloudGroupRepository implements ICloudGroupRepository {

    private final Map<String, CloudGroup> cloudGroupMap = new ConcurrentHashMap<>();

    @Override
    public CloudGroup save(CloudGroup cloudGroup) {
        cloudGroupMap.putIfAbsent(cloudGroup.getGroupName(), cloudGroup);
        return cloudGroup;
    }

    @Override
    public List<CloudGroup> fetchAll() {
        return new ArrayList<>(cloudGroupMap.values());
    }

    @Override
    public CloudGroup fetch(String groupName) {
        if (!cloudGroupMap.containsKey(groupName)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            cloudGroupMap.put(groupName, session.find(CloudGroup.class, groupName));
        }
        return cloudGroupMap.get(groupName);
    }

    @Override
    public void delete(String groupName) {
        cloudGroupMap.remove(groupName);
    }
}

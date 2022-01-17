package net.maybemc.cloud.http.server.repository.server;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
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
@EntityScan(basePackages = {"net.maybemc.cloud.api.cloud.entity.server"})
public class CloudServerRepository implements ICloudServerRepository {

    private final Map<String, CloudServer> cloudServerMap = new ConcurrentHashMap<>();

    @Override
    public CloudServer save(CloudServer cloudServer) {
        cloudServerMap.putIfAbsent(cloudServer.getServerName(), cloudServer);
        return cloudServer;
    }

    @Override
    public List<CloudServer> fetchAll() {
        return new ArrayList<>(cloudServerMap.values());
    }

    @Override
    public CloudServer fetch(String groupName) {
        if (!cloudServerMap.containsKey(groupName)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            cloudServerMap.put(groupName, session.find(CloudServer.class, groupName));
        }
        return cloudServerMap.get(groupName);
    }

    @Override
    public void delete(String groupName) {
        cloudServerMap.remove(groupName);
    }
}

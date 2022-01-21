package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;
import net.maybemc.cloud.service.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@CloudCommand(name = "servers", description = "overview about active servers")
public class ServersCommand implements ICloudCommand {

    private final Logger logger = LoggerFactory.getLogger(ServersCommand.class);
    private final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

    @Override
    public void onExecute(String[] args) {
        if (args.length == 0) {
            try {
                Objects.requireNonNull(cloudHttpClient.getCloudServerService().getAllCloudServers().execute().body()).forEach(server ->
                        logger.info(server.getServerName() + " | " + server.getCloudGroup().getGroupName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String groupName = args[0];
            try {
                if (cloudHttpClient.getCloudGroupService().getCloudGroup(groupName).execute().body() == null) {
                    logger.error("The group could not be found!");
                    return;
                }
                Objects.requireNonNull(cloudHttpClient.getCloudServerService().getAllCloudServersFromCloudGroup(groupName)
                                .execute().body())
                        .stream().filter(filter -> filter.getCloudGroup().getGroupName().equalsIgnoreCase(groupName))
                        .forEach(server -> logger.info(server.getServerName() + " | " + server.getCloudGroup().getGroupName()));
            } catch (IOException e) {
                logger.error("There was an error while retrieving group information!");
            }
        }
    }
}

package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;
import net.maybemc.cloud.service.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@CloudCommand(name = "groups", description = "shows all groups")
public class GroupsCommand implements ICloudCommand {

    private final Logger logger = LoggerFactory.getLogger(GroupsCommand.class);
    private final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

    @Override
    public void onExecute(String[] args) {
        try {
            Objects.requireNonNull(cloudHttpClient.getCloudGroupService().getCloudGroups().execute().body()).forEach(group ->
                    logger.info(group.getGroupName() + " | " + group.getMinServiceAmount()));
        } catch (IOException e) {
            logger.error("there was an error while fetching groups!");
        }
    }
}

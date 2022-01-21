package net.maybemc.cloud.server.command.command;

import lombok.SneakyThrows;
import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;
import net.maybemc.cloud.service.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;

@CloudCommand(name = "groupInformation", description = "retrieve information of specific cloud group", aliases = {"gi", "groupInfo"})
public class GroupInformationCommand implements ICloudCommand {

    private final Logger logger = LoggerFactory.getLogger(GroupInformationCommand.class);
    private
    final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

    @SneakyThrows
    @Override
    public void onExecute(String[] args) {
        if (args.length != 1) {
            System.out.println("groupInfo <groupName>");
            return;
        }
        String group = args[0];
        try {
            Response<CloudGroup> execute = cloudHttpClient.getCloudGroupService().getCloudGroup(group).execute();
            CloudGroup cloudGroup = execute.body();
            logger.info("group name: " + cloudGroup.getGroupName());
            logger.info("min service amount: " + cloudGroup.getMinServiceAmount());
            logger.info("max ram: " + cloudGroup.getMaxRam());
            logger.info("version: " + cloudGroup.getVersion());
            logger.info("server type: " + cloudGroup.getServerType().name());
        } catch (IOException | NullPointerException e) {
            System.out.println("there was an error while retrieving group information!");
        }
    }
}

package net.maybemc.cloud.server.command.command;

import lombok.SneakyThrows;
import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.http.client.util.ResponseUtil;
import net.maybemc.cloud.server.CloudServerApplication;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;

import java.io.IOException;

@CloudCommand(name = "groupInformation", description = "retrieve information of specific cloud group", aliases = {"gi", "groupInfo"})
public class GroupInformationCommand implements ICloudCommand {
    @SneakyThrows
    @Override
    public void onExecute(String[] args) {
        if (args.length != 1) {
            System.out.println("groupInfo <groupName>");
            return;
        }
        String group = args[0];
        try {
            final CloudGroup cloudGroup = ResponseUtil.processResponse(CloudServerApplication.getClient().getCloudGroupService().getCloudGroup(group).execute());
            if (cloudGroup == null) {
                System.out.println("the group " + group + " was not found!");
                return;
            }
            System.out.println("group name = " + cloudGroup.getGroupName());
        } catch (IOException e) {
            System.out.println("there was an error while retrieving group information!");
        }
    }
}

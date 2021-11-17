package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.server.CloudServerApplication;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;

import java.io.IOException;

@CloudCommand(name = "createGroup", description = "creates a cloud group")
public class CreateGroupCommand implements ICloudCommand {
    @Override
    public void onExecute(String[] args) {
        if (args.length != 2) {
            System.out.println("createGroup <groupName> <minServiceAmount>");
            return;
        }
        try {
            String groupName = args[0];
            int minServiceAmount = Integer.parseInt(args[1]);
            CloudGroup cloudGroup = new CloudGroup();
            cloudGroup.setGroupName(groupName);
            cloudGroup.setMinServiceAmount(minServiceAmount);
            try {
                CloudServerApplication.getClient().getCloudGroupService().createCloudGroup(cloudGroup).execute();
            } catch (IOException e) {
                System.out.println("Die Gruppe " + cloudGroup + " konnte nicht erstellt werden!");
            }
            System.out.println("created cloud group " + groupName);
        } catch (NumberFormatException exception) {
            System.out.println("Es gab einen Fehler beim erstellen der Gruppe!");
        }
    }
}

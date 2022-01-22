package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.api.cloud.entity.group.mode.ServerMode;
import net.maybemc.cloud.api.cloud.library.ServerType;
import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;
import net.maybemc.cloud.service.provider.ServiceProvider;

import java.io.IOException;

@CloudCommand(name = "createGroup", description = "creates a cloud group")
public class CreateGroupCommand implements ICloudCommand {

    private final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

    @Override
    public void onExecute(String[] args) {
        if (args.length != 6) {
            System.out.println("createGroup <groupName> <version> <PAPER_SPIGOT/BUNGEE_CORD> <STATIC/DYNAMIC> <maxRam> <minServiceAmount>");
            return;
        }
        try {
            String groupName = args[0];
            String version = args[1];
            ServerType serverType = ServerType.valueOf(args[2].toUpperCase());
            ServerMode serverMode = ServerMode.valueOf(args[3].toUpperCase());
            int maxRam = Integer.parseInt(args[4]);
            int minServiceAmount = Integer.parseInt(args[5]);

            CloudGroup cloudGroup = new CloudGroup();
            cloudGroup.setMaxRam(maxRam);
            cloudGroup.setVersion(version);
            cloudGroup.setGroupName(groupName);
            cloudGroup.setServerType(serverType);
            cloudGroup.setServerMode(serverMode);
            cloudGroup.setMinServiceAmount(minServiceAmount);

            try {
                cloudHttpClient.getCloudGroupService().createCloudGroup(cloudGroup).execute();
            } catch (IOException e) {
                System.out.println("Die Gruppe " + cloudGroup + " konnte nicht erstellt werden!");
            }
            System.out.println("created cloud group " + groupName);
        } catch (NumberFormatException | ClassCastException exception) {
            System.out.println("Es gab einen Fehler beim erstellen der Gruppe!");
        }
    }
}

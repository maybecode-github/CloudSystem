package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.ICloudCommand;

@CloudCommand(name = "stop")
public class StopCommand implements ICloudCommand {
    @Override
    public void onExecute(String[] args) {
        System.out.println("the cloud is stopping now...");
        System.exit(0);
    }

}

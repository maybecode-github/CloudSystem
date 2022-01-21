package net.maybemc.cloud.api.cloud.library;

import lombok.Getter;

public enum ServerType {

    PAPER_SPIGOT, BUNGEE_CORD;

    @Getter
    private String version;

    public ServerType setVersion(String version) {
        this.version = version;
        return this;
    }

    public static String getDownloadPath(ServerType serverType) {
        if (serverType.equals(ServerType.PAPER_SPIGOT)) {
            return "https://cdn.getbukkit.org/spigot/spigot-" + serverType.getVersion() + "-R0.1-SNAPSHOT-latest.jar";
        } else {
            return "https://ci.md-5.net/job/BungeeCord/lastSuccessfulBuild/artifact/bootstrap/target/BungeeCord.jar";
        }
    }

}

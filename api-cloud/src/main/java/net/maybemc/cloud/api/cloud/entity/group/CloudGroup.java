package net.maybemc.cloud.api.cloud.entity.group;

import lombok.Getter;
import lombok.Setter;
import net.maybemc.cloud.api.cloud.entity.group.mode.ServerMode;
import net.maybemc.cloud.api.cloud.library.ServerType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class CloudGroup {

    @Id
    private String groupName;
    private String version;

    @Enumerated(EnumType.STRING)
    private ServerType serverType;
    @Enumerated(EnumType.STRING)
    private ServerMode serverMode;

    private int maxRam;
    private int minServiceAmount;

}

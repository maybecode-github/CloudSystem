package net.maybemc.cloud.api.cloud.entity.server;

import lombok.Getter;
import lombok.Setter;
import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CloudServer {

    @Id
    private String serverName;

    @ManyToOne
    @JoinColumn(name = "groupName")
    private CloudGroup cloudGroup;

}

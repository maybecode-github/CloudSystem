package net.maybemc.cloud.api.cloud.entity.group;

import lombok.Getter;
import lombok.Setter;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
public class CloudGroup {

    @Id
    private String groupName;
    private int minServiceAmount;

}

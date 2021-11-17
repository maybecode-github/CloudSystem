package net.maybemc.cloud.api.cloud.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CloudResponseEntity<T> {

    private final T body;
    private final boolean success;

}

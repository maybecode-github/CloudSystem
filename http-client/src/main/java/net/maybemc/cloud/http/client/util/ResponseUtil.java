package net.maybemc.cloud.http.client.util;

import net.maybemc.cloud.api.cloud.entity.response.CloudResponseEntity;
import retrofit2.Response;

public final class ResponseUtil {

    public static <T> T processResponse(final Response response) {
        if (!response.isSuccessful()) {
            throw new RuntimeException("Request failed: " + response.message() + " status " + response.code() + " body: " + response.errorBody());
        } else if (response.body() instanceof CloudResponseEntity) {
            final CloudResponseEntity cloudResponseEntity = (CloudResponseEntity) response.body();
            if (cloudResponseEntity.isSuccess()) {
                return (T) cloudResponseEntity.getBody();
            } else {
                return null;
            }
        }
        return (T) response.body();
    }


}

package net.maybemc.cloud.http.client.service;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.api.cloud.entity.response.CloudResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface CloudGroupService {

    @POST("cloudGroup/create")
    Call<CloudResponseEntity<CloudGroup>> createCloudGroup(@Body final CloudGroup cloudGroup);

    @GET("cloudGroup/get")
    Call<CloudResponseEntity<CloudGroup>> getCloudGroup(@Query("groupName") final String groupName);

    @GET("cloudGroups")
    Call<List<CloudGroup>> getCloudGroups();

}

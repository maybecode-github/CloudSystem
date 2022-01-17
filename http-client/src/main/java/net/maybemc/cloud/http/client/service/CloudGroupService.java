package net.maybemc.cloud.http.client.service;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;
import java.util.Optional;

public interface CloudGroupService {

    @POST("cloudGroup/create")
    Call<CloudGroup> createCloudGroup(@Body CloudGroup cloudGroup);

    @GET("cloudGroup/get")
    Call<Optional<CloudGroup>> getCloudGroup(@Query("groupName") String groupName);

    @GET("cloudGroups")
    Call<List<CloudGroup>> getCloudGroups();

}

package net.maybemc.cloud.http.client.service;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CloudServerService {

    @POST("cloudServer/create")
    Call<CloudServer> createCloudServer(@Body CloudServer cloudServer);

    @DELETE("cloudServer/delete")
    Call<CloudServer> deleteCloudServer(@Query("serverName") String serverName);

    @GET("cloudServer/get")
    Call<CloudServer> getCloudServer(@Query("serverName") String serverName);

    @GET("cloudServer/all")
    Call<List<CloudServer>> getAllCloudServers();

    @GET("cloudServer/get/all")
    Call<List<CloudServer>> getAllCloudServersFromCloudGroup(@Query("serverName") String groupName);

}

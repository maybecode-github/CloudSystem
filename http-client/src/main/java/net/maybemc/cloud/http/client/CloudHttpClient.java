package net.maybemc.cloud.http.client;

import lombok.Getter;
import net.maybemc.cloud.http.client.service.CloudGroupService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
public class CloudHttpClient {

    private final CloudGroupService cloudGroupService;

    public CloudHttpClient(String baseUrl, String apiKey) {
        final OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder().addHeader("X-API-KEY", apiKey).build())).build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient).addConverterFactory(GsonConverterFactory.create()).build();

        cloudGroupService = retrofit.create(CloudGroupService.class);
    }

}

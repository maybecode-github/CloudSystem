package net.maybemc.cloud.http.client;

import lombok.Getter;
import net.maybemc.cloud.http.client.service.CloudGroupService;
import net.maybemc.cloud.service.provider.IServiceProvider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Getter
public class CloudHttpClient implements IServiceProvider {

    private final CloudGroupService cloudGroupService;

    public CloudHttpClient(String baseUrl, String apiKey) {
        final OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder().addHeader("X-API-KEY", apiKey).build())).build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

        cloudGroupService = retrofit.create(CloudGroupService.class);
    }

}

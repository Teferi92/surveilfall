package es.santirivera.surveilfall.di.modules;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.NetworkManagerImpl;
import es.santirivera.surveilfall.data.net.WServices;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServicesModule {

    @Provides
    @Singleton
    public WServices provideWServices(Retrofit retrofit) {
        return retrofit.create(WServices.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, final Context context, final NetworkManager manager) {
        Cache cache = new Cache(context.getCacheDir(), 50 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (manager.isInternetAvailable(context)) {
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                } else {
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                }
                return chain.proceed(request);
            }
        }).build();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.scryfall.com")
                .build();
    }

    @Singleton
    @Provides
    NetworkManager providesNetworkManager(Context context) {
        return new NetworkManagerImpl(context);
    }
}
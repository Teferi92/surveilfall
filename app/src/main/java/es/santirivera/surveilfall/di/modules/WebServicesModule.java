package es.santirivera.surveilfall.di.modules;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.NetworkManagerImpl;
import es.santirivera.surveilfall.data.net.WServices;
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
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.scryfall.com")
                .build();
    }

    @Singleton
    @Provides
    NetworkManager providesNetworkManager(Context context) {
        return new NetworkManagerImpl(context);
    }
}

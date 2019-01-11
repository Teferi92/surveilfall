package es.santirivera.surveilfall.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.surveilfall.data.net.NetworkManager;
import es.santirivera.surveilfall.data.net.WServices;
import es.santirivera.surveilfall.data.repository.AppRepository;
import es.santirivera.surveilfall.data.repository.AppRepositoryImpl;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public AppRepository provideAppRepository(
            Context context,
            WServices wServices,
            NetworkManager networkManager) {

        return new AppRepositoryImpl(context,
                wServices,
                networkManager);
    }

}

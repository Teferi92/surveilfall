package es.santirivera.surveilfall.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {

    @Provides
    @Singleton
    public Gson instanceGson() {
        return new GsonBuilder().create();
    }

}

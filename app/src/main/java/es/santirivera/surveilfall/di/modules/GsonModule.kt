package es.santirivera.surveilfall.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class GsonModule {

    @Provides
    @Singleton
    fun instanceGson(): Gson {
        return GsonBuilder().create()
    }

}

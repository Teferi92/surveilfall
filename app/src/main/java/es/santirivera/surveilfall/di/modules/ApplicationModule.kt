package es.santirivera.surveilfall.di.modules

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.base.app.AndroidApplication

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideSanApplication(): AndroidApplication {
        return this.application
    }

}
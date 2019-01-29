package es.santirivera.surveilfall.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class RealmModule {

    @Provides
    @Singleton
    fun instanceRealm(): Realm {
        return Realm.getDefaultInstance()
    }

}

package es.santirivera.surveilfall.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.data.net.GithubWebServices
import es.santirivera.surveilfall.data.net.ScryfallWebServices
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.data.repository.AppRepositoryImpl
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.data.repository.DatabaseRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
            scryfall: ScryfallWebServices,
            github: GithubWebServices): AppRepository {
        return AppRepositoryImpl(scryfall, github)
    }

    @Provides
    @Singleton
    fun provideDBRepository(context: Context): DBRepository {
        return DatabaseRepository(context)
    }

}

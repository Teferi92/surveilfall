package es.santirivera.surveilfall.di.modules

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.base.UseCaseHandler
import es.santirivera.surveilfall.domain.usecases.base.UseCaseScheduler
import es.santirivera.surveilfall.domain.usecases.base.UseCaseThreadPoolScheduler
import es.santirivera.surveilfall.domain.usecases.providers.UseCaseProvider

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideUseCaseProvider(appRepository: AppRepository, dbRepository: DBRepository): UseCaseProvider {
        return UseCaseProvider(appRepository, dbRepository)
    }

    @Singleton
    @Provides
    fun provideUseCaseHandler(useCaseScheduler: UseCaseScheduler): UseCaseHandler {
        return UseCaseHandler(useCaseScheduler)
    }

    @Singleton
    @Provides
    fun provideUseCaseScheduler(useCaseThreadPoolScheduler: UseCaseThreadPoolScheduler): UseCaseScheduler {
        return useCaseThreadPoolScheduler
    }
}

package es.santirivera.surveilfall.domain.use_cases.providers

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.use_cases.GetArtistNamesUseCase
import es.santirivera.surveilfall.domain.use_cases.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.use_cases.GetSetsUseCase

class UseCaseProvider(private val appRepository: AppRepository) {

    val getSetsUseCase: GetSetsUseCase
        get() = GetSetsUseCase(appRepository)

    val getArtistNamesUseCase: GetArtistNamesUseCase
        get() = GetArtistNamesUseCase(appRepository)

    val getCardsForQueryUseCase: GetCardsForQueryUseCase
        get() = GetCardsForQueryUseCase(appRepository)

}
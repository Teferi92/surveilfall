package es.santirivera.surveilfall.domain.usecases.providers

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.GetArtistNamesUseCase
import es.santirivera.surveilfall.domain.usecases.GetCardInSetUseCase
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.GetSetsUseCase

class UseCaseProvider(private val appRepository: AppRepository) {

    val getSetsUseCase: GetSetsUseCase
        get() = GetSetsUseCase(appRepository)

    val getArtistNamesUseCase: GetArtistNamesUseCase
        get() = GetArtistNamesUseCase(appRepository)

    val getCardsForQueryUseCase: GetCardsForQueryUseCase
        get() = GetCardsForQueryUseCase(appRepository)

    val getCardInSetUseCase: GetCardInSetUseCase
        get() = GetCardInSetUseCase(appRepository)
}
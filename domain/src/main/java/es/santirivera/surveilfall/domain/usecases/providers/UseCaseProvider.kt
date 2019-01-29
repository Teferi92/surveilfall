package es.santirivera.surveilfall.domain.usecases.providers

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.domain.usecases.*

class UseCaseProvider(private val appRepository: AppRepository) {

    val getSetsUseCase: GetSetsUseCase
        get() = GetSetsUseCase(appRepository)

    val getArtistNamesUseCase: GetArtistNamesUseCase
        get() = GetArtistNamesUseCase(appRepository)

    val getCardsForQueryUseCase: GetCardsForQueryUseCase
        get() = GetCardsForQueryUseCase(appRepository)

    val getCardInSetUseCase: GetCardInSetUseCase
        get() = GetCardInSetUseCase(appRepository)

    val getRandomCardUseCase: GetRandomCardUseCase
        get() = GetRandomCardUseCase(appRepository)

}
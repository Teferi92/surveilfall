package es.santirivera.surveilfall.domain.usecases.providers

import es.santirivera.surveilfall.data.repository.AppRepository
import es.santirivera.surveilfall.data.repository.DBRepository
import es.santirivera.surveilfall.domain.usecases.implementation.artist.GetArtistNamesUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.bitmap.GetBitmapFromURLUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetRandomCardUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.*
import es.santirivera.surveilfall.domain.usecases.implementation.sets.GetSetsUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.ClearWordBankUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.GetWordBankUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.UpdateWordBankUseCase

class UseCaseProvider(private val appRepository: AppRepository, private val dbRepository: DBRepository) {

    val getSetsUseCase: GetSetsUseCase
        get() = GetSetsUseCase(appRepository)

    val getArtistNamesUseCase: GetArtistNamesUseCase
        get() = GetArtistNamesUseCase(appRepository)

    val getCardsForQueryUseCase: GetCardsForQueryUseCase
        get() = GetCardsForQueryUseCase(appRepository)

    val getRandomCardUseCase: GetRandomCardUseCase
        get() = GetRandomCardUseCase(appRepository)

    val getBitmapFromURLUseCase: GetBitmapFromURLUseCase
        get() = GetBitmapFromURLUseCase()

    val getWordBankUseCase: GetWordBankUseCase
        get() = GetWordBankUseCase(dbRepository)

    val updateWordBankUseCase: UpdateWordBankUseCase
        get() = UpdateWordBankUseCase(appRepository, dbRepository)

    val addFavoriteUseCase: AddFavoriteUseCase
        get() = AddFavoriteUseCase(dbRepository)

    val removeFavoriteUseCase: RemoveFavoriteUseCase
        get() = RemoveFavoriteUseCase(dbRepository)

    val isFavoriteUseCase: IsFavoriteUseCase
        get() = IsFavoriteUseCase(dbRepository)

    val getFavoritesUseCase: GetFavoritesUseCase
        get() = GetFavoritesUseCase(dbRepository)

    val clearFavoritesUseCase: ClearFavoritesUseCase
        get() = ClearFavoritesUseCase(dbRepository)

    val clearWordBankUseCase: ClearWordBankUseCase
        get() = ClearWordBankUseCase(dbRepository)

}
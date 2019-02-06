package es.santirivera.surveilfall.data.repository

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Favorite
import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse

interface DBRepository {

    // Word Bank
    fun getWordBank(): RepositoryResponse<List<WordBankItem>>

    fun updateWordBank(wordBank: List<WordBankItem>)

    // Favorites

    fun getFavorites(): RepositoryResponse<List<Favorite>>

    fun isFavorite(cardId: String): Boolean

    fun addFavorite(card: Card)

    fun removeFavorite(card: Card)

}
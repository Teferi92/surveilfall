package es.santirivera.surveilfall.data.repository

import android.content.Context
import androidx.room.Room
import es.santirivera.surveilfall.data.db.SurveilfallDB
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Favorite
import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.data.repository.responses.LocalResponse
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse

class DatabaseRepository(val context: Context) : DBRepository {

    private val db = Room.databaseBuilder(context,
            SurveilfallDB::class.java, "surveilfall"
    ).build()

    override fun getWordBank(): RepositoryResponse<List<WordBankItem>> {
        val wordBank = db.wordBankItemDao().loadWordBankItems()
        return LocalResponse(wordBank)
    }

    override fun updateWordBank(wordBank: List<WordBankItem>) {
        val dao = db.wordBankItemDao()
        for (word in wordBank) {
            dao.insert(word)
        }
    }

    override fun getFavorites(): RepositoryResponse<List<Favorite>> {
        val favorites = db.favoriteDao().loadFavorites()
        return LocalResponse(favorites)
    }

    override fun isFavorite(cardId: String): Boolean {
        return db.favoriteDao().isFavorite(cardId) == 1
    }

    override fun addFavorite(card: Card) {
        val favorite = Favorite(card.id, card, true)
        db.favoriteDao().insert(favorite)
    }

    override fun removeFavorite(card: Card) {
        val favorite = Favorite(card.id, card, false)
        db.favoriteDao().insert(favorite)
    }

}

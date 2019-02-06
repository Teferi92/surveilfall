package es.santirivera.surveilfall.data.db.dao

import androidx.room.*
import es.santirivera.surveilfall.data.model.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE is_favorite == 1")
    fun loadFavorites(): List<Favorite>

    /**
     * Returns 1 if it's a favorite, 0 if it's not
     */
    @Query("SELECT count(*) from (SELECT * FROM favorite WHERE card_id == :cardId AND is_favorite == 1)")
    fun isFavorite(cardId: String): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Favorites: Favorite)

}
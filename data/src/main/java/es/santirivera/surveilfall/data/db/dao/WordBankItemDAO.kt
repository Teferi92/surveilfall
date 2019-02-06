package es.santirivera.surveilfall.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.santirivera.surveilfall.data.model.WordBankItem

@Dao
interface WordBankItemDAO {

    @Query("SELECT * FROM WordBankItem")
    fun loadWordBankItems(): List<WordBankItem>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(item: WordBankItem)

}
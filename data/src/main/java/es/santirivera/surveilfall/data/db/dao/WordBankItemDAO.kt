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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WordBankItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<WordBankItem>)

    @Query("DELETE FROM WordBankItem")
    fun clear()
}
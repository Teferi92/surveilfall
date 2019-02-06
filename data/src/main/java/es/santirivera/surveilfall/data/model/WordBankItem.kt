package es.santirivera.surveilfall.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordBankItem(@PrimaryKey val name: String = "")
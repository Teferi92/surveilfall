package es.santirivera.surveilfall.data.repository


import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardList
import java.util.ArrayList

import es.santirivera.surveilfall.data.model.SetList
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse

interface AppRepository {

    val tag: String

    val setList: RepositoryResponse<SetList>

    val artistNames: RepositoryResponse<List<String>>

    fun cardsForQuery(query: String, page: Int, prints: String): RepositoryResponse<CardList>

}


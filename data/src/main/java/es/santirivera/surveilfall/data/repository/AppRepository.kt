package es.santirivera.surveilfall.data.repository


import es.santirivera.surveilfall.data.model.*
import es.santirivera.surveilfall.data.repository.responses.RepositoryResponse

interface AppRepository {

    fun getSetList(): RepositoryResponse<SetList>

    fun getArtistNames(): RepositoryResponse<List<String>>

    fun getWordBank(): RepositoryResponse<List<String>>

    fun getCardsForQuery(query: String, page: Int, prints: String, sortMethod: String, sortDirection: String): RepositoryResponse<CardList>

    fun getCardInSet(setCode: String, cardInSet: Int): RepositoryResponse<Card>

    fun getRandomCard(query: String): RepositoryResponse<Card>

    fun getTournamentURLs(): RepositoryResponse<TournamentURLs>

    fun getTournament(format: String, date: String): RepositoryResponse<Tournament>

    fun getFormats(): RepositoryResponse<List<Format>>

    fun getCardCollection(list: List<Identifier>): RepositoryResponse<List<Card>>

}


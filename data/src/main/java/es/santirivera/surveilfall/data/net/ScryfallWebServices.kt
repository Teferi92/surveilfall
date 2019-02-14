package es.santirivera.surveilfall.data.net

import es.santirivera.surveilfall.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface ScryfallWebServices {

    @get:GET("/sets")
    val setList: Call<SetList>

    @get:GET("/catalog/artist-names")
    val artistNames: Call<Catalog>

    @get:GET("/catalog/word-bank")
    val wordBank: Call<Catalog>

    @POST("/cards/collection")
    fun cardCollection(@Body list: IdentifierList): Call<CardList>

    @GET("/cards/search")
    fun cardsForQuery(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("unique") prints: String,
            @Query("order") sortType: String,
            @Query("dir") sortOrder: String
    ): Call<CardList>

    @GET("/cards/{setCode}/{numberInSet}")
    fun cardInSet(
            @Path("setCode") setCode: String,
            @Path("numberInSet") numberInSet: Int
    ): Call<Card>

    @GET("/cards/random")
    fun randomCard(
            @Query("q") query: String
    ): Call<Card>

}
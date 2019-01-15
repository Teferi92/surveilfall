package es.santirivera.surveilfall.data.net

import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardList
import es.santirivera.surveilfall.data.model.Catalog
import es.santirivera.surveilfall.data.model.SetList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WServices {

    @get:GET("/sets")
    val setList: Call<SetList>

    @get:GET("/catalog/artist-names")
    val artistNames: Call<Catalog>

    @GET("/cards/search")
    fun cardsForQuery(@Query("q") artist: String, @Query("page") page: Int, @Query("unique") prints: String): Call<CardList>

    @GET("/cards/{setCode}/{numberInSet}")
    fun cardInSet(@Path("setCode") setCode: String, @Path("numberInSet") numberInSet: Int): Call<Card>

}
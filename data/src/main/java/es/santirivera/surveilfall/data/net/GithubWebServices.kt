package es.santirivera.surveilfall.data.net

import es.santirivera.surveilfall.data.model.Format
import es.santirivera.surveilfall.data.model.Tournament
import es.santirivera.surveilfall.data.model.TournamentURLs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubWebServices {

    @get:GET("/Teferi92/challenge-data/master/challenges.json")
    val tournamentList: Call<TournamentURLs>

    @get:GET("/Teferi92/challenge-data/master/formats.json")
    val formats: Call<List<Format>>

    @GET("/Teferi92/challenge-data/master/{format}/{date}.json")
    fun tournament(
            @Path("format") format: String,
            @Path("date") date: String
    ): Call<Tournament>

}
package es.santirivera.surveilfall.data.net

import es.santirivera.surveilfall.data.model.SetList
import retrofit2.Call
import retrofit2.http.GET

interface WServices {

    @get:GET("/sets")
    val setList: Call<SetList>
}
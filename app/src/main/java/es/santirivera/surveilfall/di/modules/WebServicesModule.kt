package es.santirivera.surveilfall.di.modules

import android.content.Context

import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.data.net.NetworkManager
import es.santirivera.surveilfall.data.net.NetworkManagerImpl
import es.santirivera.surveilfall.data.net.ScryfallWebServices
import es.santirivera.surveilfall.data.net.GithubWebServices
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebServicesModule {

    @Provides
    @Singleton
    fun provideScryfallWebServices(gson: Gson, context: Context, manager: NetworkManager): ScryfallWebServices {
        val cache = Cache(context.cacheDir, (50 * 1024 * 1024).toLong())
        val okHttpClient = OkHttpClient.Builder().cache(cache).addInterceptor { chain ->
            val request = chain.request()
            if (manager.isInternetAvailable(context)) {
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            } else {
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            }
            chain.proceed(request)
        }.build()
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.scryfall.com")
                .build().create(ScryfallWebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubWebServices(gson: Gson): GithubWebServices {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://raw.githubusercontent.com/")
                .build().create(GithubWebServices::class.java)
    }


    @Singleton
    @Provides
    internal fun providesNetworkManager(context: Context): NetworkManager {
        return NetworkManagerImpl(context)
    }
}
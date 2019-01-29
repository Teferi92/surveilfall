package es.santirivera.surveilfall.di.modules

import android.content.Context

import com.google.gson.Gson

import java.io.IOException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.data.net.NetworkManager
import es.santirivera.surveilfall.data.net.NetworkManagerImpl
import es.santirivera.surveilfall.data.net.WServices
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebServicesModule {

    @Provides
    @Singleton
    fun provideWServices(retrofit: Retrofit): WServices {
        return retrofit.create(WServices::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, context: Context, manager: NetworkManager): Retrofit {
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
                .build()
    }

    @Singleton
    @Provides
    internal fun providesNetworkManager(context: Context): NetworkManager {
        return NetworkManagerImpl(context)
    }
}
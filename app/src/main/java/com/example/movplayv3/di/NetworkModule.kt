package com.example.movplayv3.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.movplayv3.BuildConfig
import com.example.movplayv3.data.remote.api.ApiParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import kotlin.time.toJavaDuration

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context) =
        Cache(context.cacheDir, ApiParams.cacheSize)

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val requestUrl = request.url
            val url = requestUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val modifiedRequest = request.newBuilder()
                .url(url)
                .build()
            chain.proceed(modifiedRequest)
        }
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val collector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            .collector(collector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        authenticationInterceptor: Interceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(loggingInterceptor)
            }
        }
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(authenticationInterceptor)
        .cache(cache)
        .connectTimeout(ApiParams.Timeouts.connect.toJavaDuration())
        .writeTimeout(ApiParams.Timeouts.write.toJavaDuration())
        .readTimeout(ApiParams.Timeouts.read.toJavaDuration())
        .build()
}
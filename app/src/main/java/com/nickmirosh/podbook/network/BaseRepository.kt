package com.nickmirosh.podbook.network

import com.google.gson.GsonBuilder
import com.nickmirosh.podbook.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val apiKey = "X-ListenAPI-Key"
const val connectTimeOutSeconds = 30L
const val readTimeOutSeconds = 30L


abstract class BaseRepository {


    fun buildRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(connectTimeOutSeconds, TimeUnit.SECONDS)
            .readTimeout(readTimeOutSeconds, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                var request: Request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter(apiKey, BuildConfig.API_KEY)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}
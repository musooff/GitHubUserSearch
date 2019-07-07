package com.ballboycorp.anappaday.github.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by musooff on 2019-07-05.
 */
class GithubService {

    companion object {

        private const val BASE_URL = "https://api.github.com"

        @Volatile
        private var api: GithubAPI? = null

        fun getApi(): GithubAPI =
            api ?: synchronized(this) {
                api ?: startService(BASE_URL)
                    .also { api = it }
            }

        private fun startService(baseUrl: String): GithubAPI {
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(
                    GsonConverterFactory
                        .create()
                )
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

            return retrofit.create(GithubAPI::class.java)
        }
    }

    fun users(query: String, page: Int, perPage: Int) = getApi().users(query, page, perPage)
}
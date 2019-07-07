package com.ballboycorp.anappaday.github.network

import com.ballboycorp.anappaday.github.model.user.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by musooff on 2019-07-05.
 */
interface GithubAPI {

    @GET("/search/users")
    fun users(@Query(value = "q") query: String): Observable<User.Result>
}
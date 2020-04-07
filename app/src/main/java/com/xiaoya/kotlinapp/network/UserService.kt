package com.xiaoya.kotlinapp.network

import com.xiaoya.kotlinapp.network.entities.User
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
interface UserApi{
    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>

    @GET("/users")
    fun allUsers(@Query("since") since: Int, @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<User>>

    @GET("/users/{login}/following")
    fun following(@Path("login") login: String, @Query("page") page: Int = 1, @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<User>>

    @GET("/users/{login}/followers")
    fun followers(@Path("login") login: String, @Query("page") page: Int = 1): Observable<GitHubPaging<User>>
}

object UserService : UserApi by retrofit.create(UserApi::class.java)
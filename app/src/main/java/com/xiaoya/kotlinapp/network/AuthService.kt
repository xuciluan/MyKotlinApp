package com.xiaoya.kotlinapp.network

import com.xiaoya.kotlinapp.network.entities.AuthorizationReq
import com.xiaoya.kotlinapp.network.entities.AuthorizationRsp
import com.xiaoya.kotlinapp.settings.Configs
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
import rx.Observable

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/

interface AuthApi{

    @PUT("/authorizations/clients/${Configs.Accout.clientId}/{fingerPrint}")
    fun  createAuthorization(@Body req:AuthorizationReq, @Path("fingerPrint") fingerPrint : String = Configs.Accout.fingerPrint)
    :Observable<AuthorizationRsp>


    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>
}

object AuthService : AuthApi by retrofit.create(AuthApi::class.java)

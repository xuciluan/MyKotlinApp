package com.xiaoya.kotlinapp.github.model.account


import com.google.gson.Gson
import com.xiaoya.kotlinapp.network.AuthService
import com.xiaoya.kotlinapp.network.UserApi
import com.xiaoya.kotlinapp.network.UserService
import com.xiaoya.kotlinapp.network.entities.AuthorizationReq
import com.xiaoya.kotlinapp.network.entities.AuthorizationRsp
import com.xiaoya.kotlinapp.network.entities.User
import com.xiaoya.kotlinapp.utils.fromJson2
import com.xiaoya.kotlinapp.utils.pref
import retrofit2.HttpException
import rx.Observable

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/

interface OnAccountStateChangeListener {
    fun onLogin(user: User)

    fun onLogout()
}

object AccountManager {
    var authId by pref(-1)
    var userName by pref("")
    var passwd by pref("")
    var token by pref("")

    private var userJson by pref("")

    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson2<User>(userJson)
            }
            return field
        }
        set(value) {
            if (value == null) {
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    val onAccountStateChangeListener = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListener.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLogout() {
        onAccountStateChangeListener.forEach {
            it.onLogout()
        }
    }

    fun isloggedIn(): Boolean = token.isNotEmpty()

    fun login() =
            AuthService.createAuthorization(
                    AuthorizationReq()
            )
                    .doOnNext {
                        if (it.token.isEmpty()) throw AccountException(it)
                    }.retryWhen {
                        it.flatMap {
                            if (it is AccountException) {
                                AuthService.deleteAuthorization(it.authorizationRsp.id)
                            } else {
                                Observable.error(it)
                            }
                        }
                    }
                    .flatMap {
                        token = it.token
                        authId = it.id
                        UserService.getAuthenticatedUser()
                    }
                    .map {
                        currentUser = it
                        notifyLogin(it)
                    }


    fun logout() =
        AuthService.deleteAuthorization(authId)
                .doOnNext {
                    if (it.isSuccessful) {
                        authId = -1
                        token = ""
                        currentUser = null
                        notifyLogout()
                    } else {
                        throw HttpException(it)
                    }
                }


    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")

}
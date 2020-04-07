package com.xiaoya.kotlinapp.network.entities

import com.xiaoya.common.PoKo
import com.xiaoya.kotlinapp.settings.Configs

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/
@PoKo
data class AuthorizationReq(
        var scope: List<String> = Configs.Accout.SCOPES,
        var note : String = Configs.Accout.note,
        var note_url : String = Configs.Accout.noteUrl,
        var client_secret : String  = Configs.Accout.clientSecret
)

@PoKo
data class  AuthorizationRsp(
        var id : Int,
        var url :String,
        var app : App,
        var token : String,
        var hashed_token : String,
        var token_last_eight : String,
        var note : String,
        var note_url : String,
        var created_at : String,
        var update_at :String,
        var scopes : List<String>
)

@PoKo
data class App(
        var name : String,
        var url : String,
        var client_id : String
)



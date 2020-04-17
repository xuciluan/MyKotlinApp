package com.xiaoya.kotlinapp.utils

import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/

fun AvatarImageView.loadWithGlide(url:String,textPlaceHolder:Char,requestOptions:RequestOptions = RequestOptions()){
    textPlaceHolder.toString().let {
        setTextAndColorSeed(it.toUpperCase(),it.hashCode().toString())
    }

    Glide.with(this.context)
            .load(url)
            .apply(requestOptions)
            .into(this)
}
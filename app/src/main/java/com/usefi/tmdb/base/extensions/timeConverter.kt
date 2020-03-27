package com.usefi.tmdb.base.extensions

fun  String.toMinute(str : String) : String{
    val hour = str.toInt()/60
    val minute = str.toInt()%60
    return  hour.toString() + "h  " + minute + "m"
}
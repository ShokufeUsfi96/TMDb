package com.usefi.tmdb.base.di

import com.usefi.tmdb.repository.NetworkRepository
import com.usefi.tmdb.utils.apiBaseURL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retroModule = module {
    single { Retrofit.Builder()
        .baseUrl( apiBaseURL )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    }

    single { NetworkRepository( get() )  }
}
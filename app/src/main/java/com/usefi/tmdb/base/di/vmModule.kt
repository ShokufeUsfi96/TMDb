package com.usefi.tmdb.base.di

import com.usefi.tmdb.features.MovieDetailActivity.DetailViewModel
import com.usefi.tmdb.features.SearchActivity.SearchViewModel
import com.usefi.tmdb.repository.Repository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val vmModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
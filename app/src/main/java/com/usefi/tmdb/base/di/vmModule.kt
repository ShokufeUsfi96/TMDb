package com.usefi.tmdb.base.di

import com.usefi.tmdb.features.DetailMovie.DetailViewModel
import com.usefi.tmdb.features.FavoritesMovie.FavoritesViewModel
import com.usefi.tmdb.features.SearchMovie.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoritesViewModel() }

}
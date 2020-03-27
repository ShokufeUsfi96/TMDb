package com.usefi.tmdb.base

import android.app.Application
import com.usefi.tmdb.base.di.retroModule
import com.usefi.tmdb.base.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(vmModule, retroModule)
        }


    }
}
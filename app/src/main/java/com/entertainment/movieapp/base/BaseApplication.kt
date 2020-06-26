package com.entertainment.movieapp.base

import com.entertainment.movieapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Initialization of libraries.
 */
class BaseApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApplication
    }
}

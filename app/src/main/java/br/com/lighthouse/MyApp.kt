package br.com.lighthouse

import android.app.Application
import br.com.lighthouse.di.App
import br.com.lighthouse.di.AppModule
import br.com.lighthouse.di.DaggerApp

class MyApp : Application() {

    private lateinit var appComponent: App

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApp.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): App{
        return appComponent
    }

}
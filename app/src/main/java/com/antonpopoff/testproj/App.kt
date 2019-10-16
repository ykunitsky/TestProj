package com.antonpopoff.testproj

import android.app.Application
import com.antonpopoff.testproj.di.apiModule
import com.antonpopoff.testproj.di.repositoryModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(apiModule, repositoryModule)) }
    }
}

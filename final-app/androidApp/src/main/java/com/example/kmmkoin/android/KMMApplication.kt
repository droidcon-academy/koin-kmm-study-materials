package com.example.kmmkoin.android

import android.app.Application
import com.example.kmmkoin.core.kmmModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KMMApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KMMApplication)
            androidLogger()
            modules(kmmModules())
        }
    }
}
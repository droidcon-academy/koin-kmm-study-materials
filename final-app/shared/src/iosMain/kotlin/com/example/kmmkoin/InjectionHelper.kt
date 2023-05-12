package com.example.kmmkoin

import com.example.kmmkoin.core.*
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

private lateinit var koin: Koin

class Injections() : KoinComponent {
    val env : Lookup by inject()

    fun url(): String {
        return env.baseUrl
    }
}

// Wrapper for iOS to use the KoinComponent
class UserModuleWrapper() : KoinComponent {
    val module : UserModule by inject()
}

// Helper function to start Koin from iOS
fun initKoin(){
    koin = startKoin {
        modules(kmmModules())
    }.koin
}
package com.example.kmmkoin.core

import org.koin.dsl.module

// Provider of all modules, used directly from Android
fun kmmModules() = listOf(
    allModule
)

val allModule = module {
    single { UserModule() }
    factory { Lookup() }
}

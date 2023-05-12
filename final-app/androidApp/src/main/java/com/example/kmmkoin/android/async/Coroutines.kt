package com.example.kmmkoin.android.async

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class Coroutines {
}

val coroutineModule = module {
    singleOf(::Coroutines)
}
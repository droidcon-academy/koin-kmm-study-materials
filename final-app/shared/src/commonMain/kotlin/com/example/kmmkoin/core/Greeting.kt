package com.example.kmmkoin.core

import com.example.kmmkoin.Platform
import com.example.kmmkoin.getPlatform

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
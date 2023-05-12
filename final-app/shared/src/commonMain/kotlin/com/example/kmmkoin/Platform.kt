package com.example.kmmkoin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class Meta() {
    val os: String
}
package com.giedrius.slikas.sodybon.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            listOf(
                firebaseModule(),
                loginModule(),
                mainModule(),
            )
        )
    }
}

// for ios
@Suppress("unused")
fun initKoin() {
    initKoin {  }
}
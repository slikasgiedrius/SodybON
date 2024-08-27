package com.giedrius.slikas.sodybon.di

import dev.gitlive.firebase.Firebase
import org.koin.dsl.module

fun firebaseModule() = module {
    single {
        Firebase
    }
}
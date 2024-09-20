package com.giedrius.slikas.sodybon.di

import com.giedrius.slikas.sodybon.data.user.UserRepository
import com.giedrius.slikas.sodybon.data.user.UserRepositoryImpl
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.dsl.module

fun loginModule() = module {
    factory<UserRepository> { (UserRepositoryImpl(get())) }

    single {
        LoginViewModel(
            userRepository = get(),
        )
    }
}
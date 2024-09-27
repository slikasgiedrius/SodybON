package com.giedrius.slikas.sodybon.di

import com.giedrius.slikas.sodybon.data.profile.ProfileRepository
import com.giedrius.slikas.sodybon.data.profile.ProfileRepositoryImpl
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.dsl.module

fun loginModule() = module {
    factory<ProfileRepository> { (ProfileRepositoryImpl(get())) }

    single {
        LoginViewModel(
            profileRepository = get(),
        )
    }
}
package com.giedrius.slikas.sodybon.di

import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.PropertyRepositoryImpl
import com.giedrius.slikas.sodybon.screens.home.HomeViewModel
import org.koin.dsl.module

fun mainModule() = module {
    factory<PropertyRepository> { (PropertyRepositoryImpl(get())) }

    single {
        HomeViewModel(
            propertyRepository = get(),
        )
    }
}
package com.giedrius.slikas.sodybon.di

import com.giedrius.slikas.sodybon.data.article.ArticleRepository
import com.giedrius.slikas.sodybon.data.article.ArticleRepositoryImpl
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.PropertyRepositoryImpl
import com.giedrius.slikas.sodybon.data.user.UserRepository
import com.giedrius.slikas.sodybon.data.user.UserRepositoryImpl
import com.giedrius.slikas.sodybon.ui.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun mainModule() = module {
    factory<ArticleRepository> { (ArticleRepositoryImpl(get())) }
    factory<UserRepository> { (UserRepositoryImpl(get())) }
    factory<PropertyRepository> { (PropertyRepositoryImpl(get())) }

    single {
        HomeViewModel(
            articleRepository = get(),
            usersRepository = get(),
            propertyRepository = get(),
        )
    }
}
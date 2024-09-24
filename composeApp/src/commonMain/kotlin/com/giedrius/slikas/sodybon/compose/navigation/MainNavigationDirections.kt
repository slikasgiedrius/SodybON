package com.giedrius.slikas.sodybon.compose.navigation

import androidx.navigation.NavHostController

internal object MainNavigationDirections {
    object Routes {
        const val HOME = Screens.HOME
        const val ARTICLE = "${Screens.ARTICLE}/{${ArticleArgs.TITLE}}"
        const val PROFILE = Screens.PROFILE
    }

    object ArticleArgs {
        const val TITLE = "title"
    }

    internal object Screens {
        const val HOME = "Home"
        const val ARTICLE = "Article"
        const val PROFILE = "PROFILE"
    }

    object Actions {
        fun NavHostController.openArticle(articleTitle: String) {
            this.navigate("${Screens.ARTICLE}/$articleTitle")
        }
        fun NavHostController.openHome() = this.navigate(Routes.HOME)
        fun NavHostController.openProfile() = this.navigate(Routes.PROFILE)
        fun NavHostController.navigateBack() = this.popBackStack()
    }
}
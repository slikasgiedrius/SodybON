package com.giedrius.slikas.sodybon.data.article

import com.giedrius.slikas.sodybon.data.article.model.GetArticlesResponse

interface ArticleRepository {
    suspend fun getArticles(): GetArticlesResponse
}
package com.giedrius.slikas.sodybon.data.article

import com.giedrius.slikas.sodybon.data.article.model.GetArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleRepositoryImpl(
    private val httpClient: HttpClient
) : ArticleRepository {
    override suspend fun getArticles(): GetArticlesResponse {
        return httpClient
            .get("https://newsapi.org/v2/everything?q=kotlin&sortBy=popularity&language=en&apiKey=8569364b5489401ba1b119e3dc8db21b")
            .body<GetArticlesResponse>()
    }
}
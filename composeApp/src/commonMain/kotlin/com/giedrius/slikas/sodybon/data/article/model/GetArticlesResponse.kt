package com.giedrius.slikas.sodybon.data.article.model

import kotlinx.serialization.Serializable

@Serializable
data class GetArticlesResponse(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?
)
package com.giedrius.slikas.sodybon.data.article.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String,
    val url: String?,
    val urlToImage: String?
)
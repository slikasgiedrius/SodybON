package com.giedrius.slikas.sodybon

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val age: Int
)
package com.giedrius.slikas.sodybon.data.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val age: Int,
    val fullName: String
)
package com.giedrius.slikas.sodybon.data.property.model

import kotlinx.serialization.Serializable

@Serializable
data class Property(
    val name: String,
    val logoUrl: String
)
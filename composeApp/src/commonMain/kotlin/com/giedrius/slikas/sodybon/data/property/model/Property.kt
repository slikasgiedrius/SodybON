package com.giedrius.slikas.sodybon.data.property.model

import kotlinx.serialization.Serializable

@Serializable
data class Property(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val address: Address,
    val isEnabled: Boolean,
)
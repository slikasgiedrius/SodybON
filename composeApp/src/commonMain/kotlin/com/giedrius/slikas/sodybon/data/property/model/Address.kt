package com.giedrius.slikas.sodybon.data.property.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val country: String,
    val houseNumber: String,
    val municipality: String,
    val zipCode: String,
    val street: String,
)

fun Address.getShortAddress(): String {
    return "${this.city}, ${this.country}"
}
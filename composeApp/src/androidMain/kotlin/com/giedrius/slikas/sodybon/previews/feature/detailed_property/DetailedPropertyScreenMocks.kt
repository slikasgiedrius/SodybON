package com.giedrius.slikas.sodybon.previews.feature.detailed_property

import com.giedrius.slikas.sodybon.data.property.model.Address
import com.giedrius.slikas.sodybon.data.property.model.Property

fun getMockedDetailedProperty(): Property {
    return Property(
        id = "sod1-prusiskiu-dvaras",
        name = "Prūsiškių dvaras",
        imageUrl = null,
        address = Address(
            city = "Prūsiškės",
            country = "Lietuva",
            houseNumber = "28",
            municipality = "Elektrėnų sav.",
            street = "Dabinčiaus g",
            zipCode = "LT-21320",
        ),
        isEnabled = true,
    )
}
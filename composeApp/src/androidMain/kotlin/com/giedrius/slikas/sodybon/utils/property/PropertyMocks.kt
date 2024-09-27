package com.giedrius.slikas.sodybon.utils.property

import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.data.property.model.Address
import com.giedrius.slikas.sodybon.data.property.model.Property

fun getMockedPropertyList(): List<Property> {
    return mockedProperties()
}

fun Property.getMockedPropertyImage(): Int {
    return when (this.id) {
        "sod1-prusiskiu-dvaras" -> R.drawable.sod1_main
        "sod2-vantos-lapas-pirciu-rezidencija" -> R.drawable.sod2_main
        "sod3-radviliu-sodyba" -> R.drawable.sod3_main
        else -> R.drawable.compose_multiplatform
    }
}

private fun mockedProperties(): List<Property> = listOf(
    Property(
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
    ),
    Property(
        id = "sod2-vantos-lapas-pirciu-rezidencija",
        name = "Vantos Lapas - Pirčių Rezidencija",
        imageUrl = null,
        address = Address(
            city = "Penkininkai",
            country = "Lietuva",
            houseNumber = "15",
            municipality = "Elektrėnų sav.",
            street = "Dabinčiaus g",
            zipCode = "LT-21320",
        ),
        isEnabled = true,
    ),
    Property(
        id = "sod3-radviliu-sodyba",
        name = "Radvilių sodyba",
        imageUrl = null,
        address = Address(
            city = "Paežeriai I",
            country = "Lietuva",
            houseNumber = "4",
            municipality = "Anykščių r. sav.",
            street = null,
            zipCode = "LT-29225",
        ),
        isEnabled = true,
    ),
)
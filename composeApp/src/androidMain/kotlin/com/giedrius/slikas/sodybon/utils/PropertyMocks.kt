package com.giedrius.slikas.sodybon.utils

import com.giedrius.slikas.sodybon.data.property.model.Address
import com.giedrius.slikas.sodybon.data.property.model.Property

fun getMockedPropertyList(): List<Property> {
    return mockedProperties()
}

private fun mockedProperties(): List<Property> = listOf(
    Property(
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
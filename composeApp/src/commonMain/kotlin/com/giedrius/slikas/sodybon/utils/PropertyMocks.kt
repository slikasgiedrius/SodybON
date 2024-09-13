package com.giedrius.slikas.sodybon.utils

import com.giedrius.slikas.sodybon.data.property.model.Address
import com.giedrius.slikas.sodybon.data.property.model.Property

fun getMockedPropertyList(): List<Property> {
    return mockedProperties().shuffled()
}

private fun mockedProperties(): List<Property> = listOf(
    Property(
        name = "Prūsiškių dvaras",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/sodybon.appspot.com/o/sod1-prusiskiu-dvaras%2Fmain.jpg?alt=media&token=88be0ddf-f3bb-4bfb-98fc-18ba417c7d9e",
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
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/sodybon.appspot.com/o/sod2-vantos-lapas-pirciu-rezidencija%2Fmain.jpg?alt=media&token=d9163ef4-efcf-4d56-8c99-7c986a435a5a",
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
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/sodybon.appspot.com/o/sod3-radviliu-sodyba%2Fmain.jpg?alt=media&token=eb569ed2-0c0d-447e-80ca-4a5055412671",
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
package com.giedrius.slikas.sodybon.utils

import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.data.property.model.Property

fun Property.getMockedPropertyImage(): Int {
    return when (this.name) {
        "Prūsiškių dvaras" -> R.drawable.sod1_main
        "Vantos Lapas - Pirčių Rezidencija" -> R.drawable.sod2_main
        "Radvilių sodyba" -> R.drawable.sod3_main
        else -> R.drawable.compose_multiplatform
    }
}
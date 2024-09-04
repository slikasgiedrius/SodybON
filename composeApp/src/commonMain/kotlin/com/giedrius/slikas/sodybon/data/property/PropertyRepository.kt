package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.Property

interface PropertyRepository {
    suspend fun getProperties(): List<Property>
}
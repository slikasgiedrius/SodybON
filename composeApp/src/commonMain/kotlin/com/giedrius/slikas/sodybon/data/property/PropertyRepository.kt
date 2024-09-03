package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.data.user.model.User

interface PropertyRepository {
    suspend fun getProperties(): List<Property>
}
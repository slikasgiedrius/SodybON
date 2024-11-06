package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.GetPropertyListResult
import com.giedrius.slikas.sodybon.data.property.model.GetPropertyResult

interface PropertyRepository {
    suspend fun getPropertyList(): GetPropertyListResult
    suspend fun getProperty(propertyId: String): GetPropertyResult
}
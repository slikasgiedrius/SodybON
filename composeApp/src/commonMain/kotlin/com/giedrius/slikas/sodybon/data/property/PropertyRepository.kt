package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.GetPropertiesResult

interface PropertyRepository {
    suspend fun getProperties(): GetPropertiesResult
}
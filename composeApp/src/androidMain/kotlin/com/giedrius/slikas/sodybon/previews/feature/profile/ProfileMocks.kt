package com.giedrius.slikas.sodybon.previews.feature.profile

import com.giedrius.slikas.sodybon.data.profile.model.Profile

fun getMockedUser(): Profile {
    return Profile(
        uid = "mockedUid",
        email = "mockedEmail",
        phoneNumber = "mockedPhoneNumber",
        photoUrl = null,
        fullName = "mockedFullName",
        firstName = "mockedFirstName",
        lastName = "mockedLastName",
    )
}
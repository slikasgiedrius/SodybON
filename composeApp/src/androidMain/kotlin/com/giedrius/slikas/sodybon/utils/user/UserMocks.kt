package com.giedrius.slikas.sodybon.utils.user

import com.giedrius.slikas.sodybon.data.user.model.User

fun getMockedUser(): User {
    return User(
        uid = "mockedUid",
        email = "mockedEmail",
        phoneNumber = "mockedPhoneNumber",
        photoUrl = null,
        fullName = "mockedFullName",
        firstName = "mockedFirstName",
        lastName = "mockedLastName",
    )
}
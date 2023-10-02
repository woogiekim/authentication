package org.auth

interface Otp {
    fun generateUrl(): String

    fun generateOtp(): String
}

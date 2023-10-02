package org.auth

interface OtpChecker {
    fun check(otp: String): Boolean
}

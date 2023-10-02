package org.auth.totp

import de.taimos.totp.TOTP
import org.auth.OtpChecker

class TOtpChecker(
    private val totp: TOtp
) : OtpChecker {

    override fun check(otp: String): Boolean {
        return TOTP.validate(totp.hexKey, otp)
    }
}

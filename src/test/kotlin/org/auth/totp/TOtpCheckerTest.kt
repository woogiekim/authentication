package org.auth.totp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TOtpCheckerTest {

    @Test
    fun `TOTP 검증 - 맞는 OTP인 경우 true`() {
        val totp = createGoogleOtp()
        val totpChecker = TOtpChecker(totp)

        val generatedOtp = totp.generateOtp()

        assertThat(totpChecker.check(generatedOtp)).isTrue
    }

    @Test
    fun `TOTP 검증 - 다른 OTP인 경우 false`() {
        val totpChecker = TOtpChecker(createGoogleOtp())

        val fakeOtp = "000000"

        assertThat(totpChecker.check(fakeOtp)).isFalse
    }
}

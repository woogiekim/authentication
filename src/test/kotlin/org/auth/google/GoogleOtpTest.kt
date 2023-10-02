package org.auth.google

import org.assertj.core.api.Assertions.assertThat
import org.auth.DefaultSecretKeyGenerator
import org.auth.SecretKeyGenerator
import org.auth.extension.encode
import org.junit.jupiter.api.Test

class GoogleOtpTest {

    @Test
    fun `구글 OTP 생성`() {
        val googleOtp = GoogleOtp("issuer", "account")

        assertThat(googleOtp.issuer).isEqualTo("issuer")
        assertThat(googleOtp.account).isEqualTo("account")
        assertThat(googleOtp.secretKey).isNotBlank
    }

    @Test
    fun `OTP URL 생성`() {
        val googleOtp = createGoogleOtp()

        val issuer = googleOtp.issuer
        val account = googleOtp.account
        val secretKey = googleOtp.secretKey

        assertThat(googleOtp.otpUrl).isEqualTo(
            "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"
        )
    }
}

fun createGoogleOtp(
    issuer: String = "issuer",
    account: String = "account",
    secretKeyGenerator: SecretKeyGenerator = DefaultSecretKeyGenerator()
): GoogleOtp {
    return GoogleOtp(issuer, account, secretKeyGenerator)
}

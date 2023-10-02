package org.auth.totp

import org.assertj.core.api.Assertions.assertThat
import org.auth.extension.encode
import org.junit.jupiter.api.Test

class TOtpTest {

    @Test
    fun `구글 OTP 생성`() {
        val tOtp = TOtp("issuer", "account")

        assertThat(tOtp.issuer).isEqualTo("issuer")
        assertThat(tOtp.account).isEqualTo("account")
        assertThat(tOtp.secretKey).isNotBlank
    }

    @Test
    fun `OTP URL 생성`() {
        val googleOtp = createGoogleOtp()

        val issuer = googleOtp.issuer
        val account = googleOtp.account
        val secretKey = googleOtp.secretKey

        assertThat(googleOtp.generateUrl()).isEqualTo(
            "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"
        )
    }
}

fun createGoogleOtp(
    issuer: String = "issuer",
    account: String = "account",
    secretKeyGenerator: SecretKeyGenerator = DefaultSecretKeyGenerator()
): TOtp {
    return TOtp(issuer, account, secretKeyGenerator)
}

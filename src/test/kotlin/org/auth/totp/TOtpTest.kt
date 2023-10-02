package org.auth.totp

import org.assertj.core.api.Assertions.assertThat
import org.auth.DefaultSecretKeyGenerator
import org.auth.SecretKeyGenerator
import org.auth.extension.encode
import org.junit.jupiter.api.Test

class TOtpTest {

    @Test
    fun `TOTP 생성`() {
        val totp = TOtp("issuer", "account")

        assertThat(totp.issuer).isEqualTo("issuer")
        assertThat(totp.account).isEqualTo("account")
        assertThat(totp.secretKey).isNotBlank
    }

    @Test
    fun `TOTP URL 생성`() {
        val totp = createGoogleOtp()

        val issuer = totp.issuer
        val account = totp.account
        val secretKey = totp.secretKey

        assertThat(totp.generateUrl()).isEqualTo(
            "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"
        )
    }

    @Test
    fun `TOTP 숫자 생성`() {
        val totp = createGoogleOtp()

        val generatedOtp = totp.generateOtp()

        assertThat(generatedOtp)
            .hasSize(6)
            .containsPattern("\\d")
    }
}

fun createGoogleOtp(
    issuer: String = "issuer",
    account: String = "account",
    secretKeyGenerator: SecretKeyGenerator = DefaultSecretKeyGenerator()
): TOtp {
    return TOtp(issuer, account, secretKeyGenerator)
}

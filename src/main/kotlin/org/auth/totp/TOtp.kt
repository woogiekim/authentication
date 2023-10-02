package org.auth.totp

import de.taimos.totp.TOTP
import org.apache.commons.codec.binary.Base32
import org.apache.commons.codec.binary.Hex
import org.auth.DefaultSecretKeyGenerator
import org.auth.Otp
import org.auth.SecretKeyGenerator
import org.auth.extension.encode

class TOtp(
    val issuer: String,
    val account: String,
    secretKeyGenerator: SecretKeyGenerator = DefaultSecretKeyGenerator()
) : Otp {
    companion object {
        val base32 = Base32()
    }

    val secretKey: String = secretKeyGenerator.generate().run {
        base32.encodeToString(this)
    }

    val hexKey: String = this.secretKey.run {
        val bytes = base32.decode(this)

        Hex.encodeHexString(bytes)
    }

    override fun generateUrl(): String {
        return "otpauth://totp/${"$issuer:$account".encode()}?secret=${secretKey.encode()}&issuer=${issuer.encode()}"
    }

    override fun generateOtp(): String {
        return TOTP.getOTP(this.hexKey)
    }
}

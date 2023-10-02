package org.auth.totp

import java.security.SecureRandom

interface SecretKeyGenerator {

    fun generate(): ByteArray
}

class DefaultSecretKeyGenerator : SecretKeyGenerator {

    override fun generate(): ByteArray {
        val random = SecureRandom()
        val bytes = ByteArray(20)

        random.nextBytes(bytes)

        return bytes
    }
}

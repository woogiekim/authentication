package org.auth

import java.security.SecureRandom

interface SecretKeyGenerator {

    fun generate(): ByteArray
}

class DefaultSecretKeyGenerator : SecretKeyGenerator {

    companion object {
        const val SECRET_KEY_BYTES_SIZE = 20
    }

    override fun generate(): ByteArray {
        return ByteArray(SECRET_KEY_BYTES_SIZE).apply {
            SecureRandom().nextBytes(this)
        }
    }
}
